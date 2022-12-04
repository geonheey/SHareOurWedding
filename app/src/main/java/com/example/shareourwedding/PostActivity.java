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

    private EditText title, content;
    private Button mSave, mList;

    private Intent intent;
    private String couple_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        intent = getIntent();

        couple_id = intent.getStringExtra("id");

        title = findViewById(R.id.title_et);
        content = findViewById(R.id.content_et);
        mSave = findViewById(R.id.add_btn);
        mList = findViewById(R.id.list_btn);


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    /*String postId = mDatabaseRef.child("post").getRef().getKey();
                    Map<String, Object> data = new HashMap<>();
                    data.put("userID", mAuth.getCurrentUser().getUid());
                    data.put("title", mTitle.getText().toString());
                    data.put("content", mContents.getText().toString());*/

                    String getTitle = title.getText().toString();
                    String getContent = content.getText().toString();
                    String user_id = mAuth.getCurrentUser().getUid();


                    HashMap result = new HashMap<>();

                    result.put("Title", getTitle);
                    result.put("Content", getContent);


                    writeNewUser(getTitle, getContent);
                    writeNewUser2(getTitle, getContent, couple_id, user_id);

                }

            }
            private void writeNewUser(String mTitle, String mContents) {
                RecyclerItem Rinfo  = new RecyclerItem(mTitle, mContents);
                mDatabaseRef.child("userAccount").child(mAuth.getCurrentUser().getUid()).child("post").setValue(Rinfo);
            }

            private void writeNewUser2(String mTitle, String mContents, String coupleId, String userId) {
                RecyclerItem2 Rinfo2  = new RecyclerItem2(mTitle, mContents, userId);
                mDatabaseRef.child("POST").child(coupleId).push().setValue(Rinfo2);
            }

        });


        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent id = new Intent(PostActivity.this, ReviewActivity.class);
                id.putExtra("id", couple_id);

                startActivity(id);

            }
        });
    }
}