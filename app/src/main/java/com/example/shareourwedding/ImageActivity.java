package com.example.shareourwedding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Model> list;
    ImageAdapter adapter;

    //private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mFirebaseAuth;
    private Intent intent;

   // private DatabaseReference root = FirebaseDatabase.getInstance().getReference("SHOW").child("IMAGE");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        intent = getIntent();

        String couple_id = intent.getStringExtra("id");

        TextView show_id = findViewById(R.id.show_id);
        show_id.setText(couple_id);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        adapter = new ImageAdapter(ImageActivity.this ,list);

        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        mFirebaseAuth = FirebaseAuth.getInstance();

        databaseReference = database.getReference("SHOW"); // DB 테이블 연결

        Query mQuery = databaseReference.child("IMAGE");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Model model = snapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", "onCancelled");
            }
        });
    }
}
