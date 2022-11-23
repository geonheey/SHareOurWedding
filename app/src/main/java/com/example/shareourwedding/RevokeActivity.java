package com.example.shareourwedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class RevokeActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revoke);

        Button btn_tolog2 = findViewById(R.id.btn_tolog2);
        btn_tolog2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // 로그인 화면으로 이동
                Intent intent = new Intent(RevokeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}