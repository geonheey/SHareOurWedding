package com.example.shareourwedding;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class information2 extends AppCompatActivity {
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information1);

        //아이디 정의
        Button reg_btn = (Button)findViewById(R.id.reg_btn);
        final EditText wname = (EditText)findViewById(R.id.wname);
        final EditText hname = (EditText)findViewById(R.id.hname);
        final EditText place = (EditText)findViewById(R.id.place);
        final EditText date = (EditText)findViewById(R.id.date);
        mDatabase = FirebaseDatabase.getInstance().getReference("SHOW");
        
        readUser();


        //온클릭리스너
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUserHName = hname.getText().toString();
                String getUserWName = wname.getText().toString();
                String getUserDate = date.getText().toString();
                String getUserPlace = place.getText().toString();


                HashMap result = new HashMap<>();
                result.put("Hname", getUserHName); //키, 값
                result.put("Wname", getUserWName);
                result.put("Date", getUserDate);
                result.put("Place", getUserPlace);

                 writeNewUser("info",getUserHName,getUserWName,getUserPlace,getUserDate);

            }
        });
    }
    private void writeNewUser(String userId, String hname, String wname, String place, String date) {
        CoupleInfo couple  = new CoupleInfo(hname, wname, place, date);

        mDatabase.child("Couples").child(userId).setValue(couple)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(information2.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(information2.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void readUser(){
        mDatabase.child("Couple").child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.getValue(CoupleInfo.class) != null){
                    CoupleInfo post = dataSnapshot.getValue(CoupleInfo.class);
                    Log.w("FireBaseData", "getData" + post.toString());
                } else {
                    Toast.makeText(information2.this, "데이터 없음...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}