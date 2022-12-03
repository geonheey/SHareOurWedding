package com.example.shareourwedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChoiceActivity2 extends AppCompatActivity {

    private Intent intent;
    String hname, wname, date, place, id;
    TextView s_hname, s_wname, s_date, s_place, s_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice2);

        intent = getIntent();
        hname = intent.getStringExtra("hname");
        wname = intent.getStringExtra("wname");
        date = intent.getStringExtra("date");
        place = intent.getStringExtra("place");
        id = intent.getStringExtra("id");



        s_hname = findViewById(R.id.s_hname);
        s_wname = findViewById(R.id.s_wname);
        s_date = findViewById(R.id.s_date);
        s_place = findViewById(R.id.s_place);
        s_id = findViewById(R.id.s_id);

        s_hname.setText(hname);
        s_wname.setText(wname);
        s_date.setText(date);
        s_place.setText(place);
        s_id.setText(id);



        Button btn_choice1 = (Button) findViewById(R.id.btn_choice_1);
        btn_choice1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent id = new Intent(ChoiceActivity2.this, PostActivity.class);
                id.putExtra("id", id);
                startActivity(id);
            }
        });

        Button btn_choice2 = (Button) findViewById(R.id.btn_choice_2);
        btn_choice2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity2.this, ChoiceActivity2.class);
                startActivity(intent);
            }
        });
    }
}