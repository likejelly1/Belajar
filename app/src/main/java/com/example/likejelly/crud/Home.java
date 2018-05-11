package com.example.likejelly.crud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button btnLogout;
    private SharedPreferences shPref;
    private SharedPreferences.Editor shEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initial();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shPref = getApplicationContext().getSharedPreferences("EXTRA_DATABASE_SESSION", MODE_PRIVATE);
                shEditor = shPref.edit();
                shEditor.clear();
                shEditor.commit();
                Intent logout = new Intent(Home.this,createDB.class);
                startActivity(logout);
                finish();
            }
        });
    }

    private void initial()
    {
        btnLogout = findViewById(R.id.btn_logout);
    }


}
