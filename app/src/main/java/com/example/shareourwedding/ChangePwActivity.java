package com.example.shareourwedding;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePwActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ChangePwActivity";

    //define view objects
    private EditText editTextUserEmail;
    private Button btn_changepw;
    private TextView textviewMessage;
    private ProgressDialog progressDialog;
    //define firebase object
    private FirebaseAuth firebaseAuth;
    private String userEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);

        editTextUserEmail = (EditText) findViewById(R.id.editTextUserEmail);
        btn_changepw = (Button) findViewById(R.id.btn_changepw);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        userEmail = editTextUserEmail.getText().toString();

        btn_changepw.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btn_changepw){
            progressDialog.setMessage("처리중입니다. 잠시 기다려 주세요...");
            progressDialog.show();
            //비밀번호 재설정 이메일 보내기
            String emailAddress = editTextUserEmail.getText().toString().trim();
            firebaseAuth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(ChangePwActivity.this, ChangePw2Activity.class);
                                startActivity(intent);

                            }
                        }
                    });
        }
    }
}