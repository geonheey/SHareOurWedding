package com.example.shareourwedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangePw2Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw2);

        Button btn_tolog = findViewById(R.id.btn_tolog);
        btn_tolog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // 로그인 화면으로 이동
                Intent intent = new Intent(ChangePw2Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}