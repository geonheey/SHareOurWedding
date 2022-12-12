package com.example.shareourwedding;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ChoiceActivity2 extends AppCompatActivity {

    private Intent intent;
    String hname, wname, date, place, str_id, imageurl;
    TextView s_hname, s_wname, s_date, s_place, s_id;
    ImageView s_imageurl;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice2);

        intent = getIntent();
        hname = intent.getStringExtra("hname");
        wname = intent.getStringExtra("wname");
        date = intent.getStringExtra("date");
        place = intent.getStringExtra("place");
        str_id = intent.getStringExtra("id");
        imageurl = intent.getStringExtra("imageurl");

        Uri myUri = Uri.parse(imageurl);

        s_hname = findViewById(R.id.s_hname);
        s_wname = findViewById(R.id.s_wname);
        s_date = findViewById(R.id.s_date);
        s_place = findViewById(R.id.s_place);
        s_imageurl = findViewById(R.id.s_imageurl);

        s_hname.setText(hname);
        s_wname.setText(wname);
        s_date.setText(date);
        s_place.setText(place);

        Glide.with(this).load(myUri).into(s_imageurl);

        Button btn_choice1 = (Button) findViewById(R.id.btn_choice_1);
        btn_choice1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent id = new Intent(ChoiceActivity2.this, PostActivity.class);
                id.putExtra("id", str_id);
                startActivity(id);
            }
        });

        Button btn_choice2 = (Button) findViewById(R.id.btn_choice_2);
        btn_choice2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent id = new Intent(ChoiceActivity2.this, ImageMainActivity.class);
                id.putExtra("id", str_id);
                startActivity(id);
            }
        });
    }
}