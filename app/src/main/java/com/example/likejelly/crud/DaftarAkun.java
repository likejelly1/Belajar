package com.example.likejelly.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DaftarAkun extends AppCompatActivity {
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etRePassword;
    EditText etPhone;
    Button btnDaftar;
    SimpleDateFormat sdf;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);
        initial();


        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftarFunction();

            }
        });

    }

    private void initial()
    {
        etRePassword = findViewById(R.id.et_repassword);
        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPhone = findViewById(R.id.et_phone);
        btnDaftar = findViewById(R.id.btn_daftar);
        sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm;ss a");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

    }
    private void daftarFunction()
    {
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String rePassword = etRePassword.getText().toString();
        String phone = etPhone.getText().toString();
        String date = sdf.format(Calendar.getInstance().getTime().getTime());

        if (email.equals("")&& username.equals("")&& password.equals("")&& phone.equals("") && rePassword.equals(""))
        {
            Toast.makeText(getBaseContext(), "data Belum lengkap", Toast.LENGTH_SHORT).show();
        }else if (!password.equals(rePassword))
        {
            Toast.makeText(getBaseContext(), "Password tidak sesuai", Toast.LENGTH_SHORT).show();
        }
        else
        {
            register reg = new register(username,email,password,phone,date);
            mDatabaseReference.child("customer").push().setValue(reg);
            Toast.makeText(getBaseContext(), "daftar berhasil", Toast.LENGTH_SHORT).show();
        }

    }


}
