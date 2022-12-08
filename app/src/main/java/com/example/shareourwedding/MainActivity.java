package com.example.shareourwedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.MenuItem;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth;      // 파이어베이스 인증
    private DatabaseReference mDatabaseRef;  // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView textView;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SHOW");



        Button btn_tochoice = findViewById(R.id.btn_tochoice);
        btn_tochoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                startActivity(intent);
            }
        });


        //메뉴

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                // start에 지정된 Drawer 열기
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        NavigationView nav = findViewById(R.id.navigationView);
        TextView tv = findViewById(R.id.textView_suc);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id = item.getItemId();

                if (id == R.id.nav_1) {

                    showDialogLogout();

                }

                else if (id == R.id.nav_2) {

                    showDialogRevoke();
                }

                return true;
            }
        });

        View nav_header_view = nav.getHeaderView(0);
        // 사용자 이메일 정보 띄우기(메뉴)
        TextView emailTextView = nav_header_view.findViewById(R.id.textView_email);
        emailTextView.setText(mFirebaseAuth.getCurrentUser().getEmail());

        // 사용자 이메일 정보 띄우기(화면)
        TextView suc_TextView = findViewById(R.id.textView_suc);
        suc_TextView.setText(mFirebaseAuth.getCurrentUser().getEmail());



    }

    public void showDialogLogout()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("정말 로그아웃 하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                logout();
                Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", null);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialogRevoke()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("정말 회원탈퇴 하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                revoke();
                /*Intent intent = new Intent(LoginActivity.this, RevokeActivity.class);
                startActivity(intent);*/
            }
        });
        builder.setNegativeButton("NO", null);

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void logout()
    {
        mFirebaseAuth.signOut();
        finish();
    }

    private void revoke() {
        mFirebaseAuth.getCurrentUser().delete();
        mFirebaseAuth.signOut();
        finish();
    }
}


