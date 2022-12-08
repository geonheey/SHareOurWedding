package com.example.shareourwedding;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shareourwedding.CustomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CoupleInfo2> list;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private View view;
    private CustomAdapter adapter;
    private EditText et_hSearch, et_wSearch;
    private Button btnSearch;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        et_hSearch = findViewById(R.id.et_hsearch);
        et_wSearch = findViewById(R.id.et_wsearch);
        btnSearch = findViewById(R.id.btn_search);



        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);



        list = new ArrayList<>();

        adapter = new CustomAdapter(list, this);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("SHOW");







        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String hSearch = et_hSearch.getText().toString();
                String wSearch = et_wSearch.getText().toString();

                String hAndw = hSearch + ", " + wSearch;

                Query mQuery = databaseReference.child("COUPLE").orderByChild("handw").equalTo(hAndw);
                mQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            CoupleInfo2 couple2 = snapshot.getValue(CoupleInfo2.class);

                            list.add(couple2);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("MainActivity", "onCancelled");
                    }
                });
            }
        });


    }
}

