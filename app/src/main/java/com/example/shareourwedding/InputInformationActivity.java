package com.example.shareourwedding;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.DatagramPacket;
import java.util.Calendar;
import java.util.HashMap;


public class InputInformationActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;

    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_information);

        this.InitializeView();
        this.InitializeListener();

        mFirebaseAuth = FirebaseAuth.getInstance();

        //아이디 정의
        Button reg_btn = (Button) findViewById(R.id.reg_btn);
        final EditText wname = (EditText) findViewById(R.id.wname);
        final EditText hname = (EditText) findViewById(R.id.hname);
        final EditText place = (EditText) findViewById(R.id.place);
        final TextView Date = findViewById(R.id.textView_date);
        ImageButton imageButton = (ImageButton) findViewById(R.id.gallerybtn);
        mDatabase = FirebaseDatabase.getInstance().getReference("SHOW");


        //온클릭리스너
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUserHName = hname.getText().toString();
                String getUserWName = wname.getText().toString();
                String getUserDate = Date.getText().toString();
                String getUserPlace = place.getText().toString();


                HashMap result = new HashMap<>();
                result.put("Hname", getUserHName); //키, 값
                result.put("Wname", getUserWName);
                result.put("Date", getUserDate);
                result.put("Place", getUserPlace);

                writeNewUser("info", getUserHName, getUserWName, getUserPlace, getUserDate);


            }

            private void writeNewUser(String userId, String hname, String wname, String place, String date) {
                CoupleInfo couple  = new CoupleInfo(hname, wname, place, date);
                mDatabase.child("userAccount").child(mFirebaseAuth.getCurrentUser().getUid()).child("couple").setValue(couple);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri uri) {
                            imageButton.setImageURI(uri);
                        }
                    });

            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(InputInformationActivity.this);
                dlg.setTitle("알람");
                dlg.setMessage("갤러리로 이동하시겠습니까?");
                dlg.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //Intent intent = new Intent(Intent.ACTION_PICK);
                                mGetContent.launch("image/*");
                            }
                        });

                dlg.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                dlg.show();
            }
        });
    }


    public void InitializeView() {
        textView_Date = (TextView) findViewById(R.id.textView_date);
    }

    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "." + monthOfYear + "." + dayOfMonth);
            }
        };
    }

    //달력버튼 클릭

    public void OnClickHandler(View view)
    {

        Calendar cal = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        dialog.show();
    }
}

