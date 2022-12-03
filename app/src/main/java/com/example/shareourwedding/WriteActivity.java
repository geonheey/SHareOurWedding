package com.example.shareourwedding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("SHOW");

    private EditText title, content;
    private Button mSave, mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        title = findViewById(R.id.title_et);
        content = findViewById(R.id.content_et);
        mSave = findViewById(R.id.add_btn);
        mList = findViewById(R.id.list_btn);


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String getUserId =  mAuth.getCurrentUser().getUid();
                String getTitle = title.getText().toString();
                String getContent = content.getText().toString();
                String getTtAct = getTitle + ", " + getContent;



                HashMap result = new HashMap<>();
                //result.put("UserId", getUserId); //키, 값
                result.put("Title", getTitle);
                result.put("Content", getContent);
                result.put("TtAct", getTtAct);

                writeNewUser(getTitle, getContent);
                writeNewUser2(getTitle, getContent, getTtAct);
            }
            private void writeNewUser(String mTitle, String mContents) {
                Review_RecyclerItem Rinfo  = new Review_RecyclerItem(mTitle, mContents);
                mDatabaseRef.child("userAccount").child(mAuth.getCurrentUser().getUid()).child("post").setValue(Rinfo);
            }

            private void writeNewUser2(String mTitle, String mContents, String TtAct) {
                Review_RecyclerItem2 Rinfo  = new Review_RecyclerItem2(mTitle, mContents, TtAct);
                mDatabaseRef.child("POST").child(mAuth.getCurrentUser().getUid()).setValue(Rinfo);
            }

        });
        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WriteActivity.this, ReviewActivity.class);
                startActivity(intent);

                }
            });
    }
}
//        m_button.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        if(mAuth.getCurrentUser()!=null){
//        String postId = m_Database.child("post").getRef().getKey();
//        Map<String, Object> data = new HashMap<>();
//        data.put("userID", mAuth.getCurrentUser().getUid());
//        data.put("title", m_title.getText().toString());
//        data.put("content", m_content.getText().toString());
//
//        m_Database.child("post").child(mAuth.getCurrentUser().getUid()).setValue(data);
//
//        }
//        }
//
//        });