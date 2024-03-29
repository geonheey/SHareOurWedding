package com.example.shareourwedding;


import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.DatagramPacket;
import java.util.Calendar;
import java.util.HashMap;


public class InputInformationActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;

    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private final DatabaseReference root = FirebaseDatabase.getInstance().getReference("SHOW").child("COUPLE");
    private final StorageReference reference = FirebaseStorage.getInstance().getReference();

    private Uri imageUri;
    private ImageView imageView;
    String getUserHName;
    String getUserWName;
    String getUserDate;
    String getUserPlace;
    String getHandW;
    String getUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_information);

        this.InitializeView();
        this.InitializeListener();

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

        //아이디 정의
        Button reg_btn = (Button) findViewById(R.id.reg_btn);
        final EditText wname = (EditText) findViewById(R.id.wname);
        final EditText hname = (EditText) findViewById(R.id.hname);
        final EditText place = (EditText) findViewById(R.id.place);
        final TextView Date = findViewById(R.id.textView_date);
        Button cal = (Button)findViewById(R.id.button10);

        imageView = findViewById(R.id.gallerybtn);
        mDatabase = FirebaseDatabase.getInstance().getReference("SHOW");


        //온클릭리스너
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUserHName = hname.getText().toString();
                getUserWName = wname.getText().toString();
                getUserDate = Date.getText().toString();
                getUserPlace = place.getText().toString();
                getHandW = getUserHName + ", " + getUserWName;
                getUid = mFirebaseAuth.getCurrentUser().getUid();



                HashMap result = new HashMap<>();
                result.put("Hname", getUserHName); //키, 값
                result.put("Wname", getUserWName);
                result.put("Date", getUserDate);
                result.put("Place", getUserPlace);


                writeNewUser(getUid, getUserHName, getUserWName, getUserPlace, getUserDate);
                //writeNewUser2(getUid, getUserHName, getUserWName, getUserPlace, getUserDate, getHandW);

                if(imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(InputInformationActivity.this, "사진을 선택해주세요",
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(InputInformationActivity.this, "업로드 성공",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InputInformationActivity.this, ChoiceActivity.class);
                startActivity(intent);

            }

            private void writeNewUser(String idtoken, String hname, String wname, String place, String date) {
                CoupleInfo couple  = new CoupleInfo(idtoken, hname, wname, place, date);
                mDatabase.child("userAccount").child(mFirebaseAuth.getCurrentUser().getUid()).child("couple").setValue(couple);
            }

            /*private void writeNewUser2(String idtoken, String hname, String wname, String place, String date, String handw) {
                CoupleInfo2 couple2  = new CoupleInfo2(idtoken, hname, wname, place, date, handw);
                mDatabase.child("COUPLE").child(mFirebaseAuth.getCurrentUser().getUid()).setValue(couple2);
            }*/
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                activityResult.launch(galleryIntent);
            }
        });









    }


    private void uploadToFirebase(Uri uri) {

        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." +
                getFileExtension(uri));

        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //성공시

                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        //이미지 모델에 담기
                        String uploadImageUrl = uri.toString();

                        CoupleInfo2 couple = new CoupleInfo2(getUid, getUserHName, getUserWName, getUserPlace, getUserDate, getHandW, uploadImageUrl);

                        //데이터 넣기
                        root.child(mFirebaseAuth.getCurrentUser().getUid()).setValue(couple);

//                        //프로그래스바 숨김
//                        progressBar.setVisibility(View.INVISIBLE);
//
//
//
//                        imageView.setImageResource(R.drawable.ic_add_photo);
                    }
                });
            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                //프로그래스바 보여주기
//                progressBar.setVisibility(View.VISIBLE);
//
//            }
        });
//                .addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                //실패
//                Toast.makeText(InputInformationActivity.this, "업로드 실패", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private String getFileExtension(Uri uri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(cr.getType(uri));
    }





    //사진 가져오기
    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == RESULT_OK && result.getData() != null){

                        imageUri = result.getData().getData();

                        imageView.setImageURI(imageUri);
                    }
                }
            });

    //달력이벤트 처리



    public void InitializeView() {
        textView_Date = (TextView) findViewById(R.id.textView_date);
    }

    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                int y = year;
                int m = monthOfYear + 1;
                int d = dayOfMonth;

                textView_Date.setText(y + "." + m + "." + d);
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

