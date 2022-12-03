package com.example.shareourwedding;

import android.app.Activity;
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

public class PostActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("SHOW");

    private EditText mTitle, mContents;
    private Button mSave, mList;

    private Intent id;
    String couple_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        id = getIntent();

        couple_id = id.getStringExtra("id");

        mTitle = findViewById(R.id.title_et);
        mContents = findViewById(R.id.content_et);
        mSave = findViewById(R.id.add_btn);
        mList = findViewById(R.id.list_btn);


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    String postId = mDatabaseRef.child("post").getRef().getKey();
                    Map<String, Object> data = new HashMap<>();
                    data.put("userID", mAuth.getCurrentUser().getUid());
                    data.put("title", mTitle.getText().toString());
                    data.put("content", mContents.getText().toString());

                    mDatabaseRef.child(mTitle.getText().toString()).setValue(data);

                }
            }

        });
        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, ReviewActivity.class);
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