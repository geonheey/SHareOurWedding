package com.example.shareourwedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button btn_choice1 = (Button) findViewById(R.id.btn_choice_1);
        btn_choice1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, InputInformationActivity.class);
                startActivity(intent);
            }
        });

        Button btn_choice2 = (Button) findViewById(R.id.btn_choice_2);
        btn_choice2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}