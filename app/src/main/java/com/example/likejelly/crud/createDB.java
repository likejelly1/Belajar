package com.example.likejelly.crud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class createDB extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button btnSignin;
    TextView tvSignup;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseReference;
    private SharedPreferences shPref;
    private SharedPreferences.Editor shEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_db);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignin = findViewById(R.id.btn_signin);
        tvSignup = findViewById(R.id.tv_signup);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("customer");


        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDaftar = new Intent(createDB.this,DaftarAkun.class);
                startActivity(goDaftar);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });

        setSharedPreferenced();

    }
//    private void loginFunction(String email, String pass){
//        mFirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Intent intent = new Intent(createDB.this, Home.class);
//                    startActivity(intent);
//                }
//                else
//                    Toast.makeText(getBaseContext(), "Email Anda Salah", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    private void login(final String email, final String pass)
    {
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isLogin = false;
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren())
                {
                    if (areaSnapshot.child("email").getValue(String.class).equals(email)&& areaSnapshot.child("password").getValue(String.class).equals(pass))
                    {
                        shEditor.putBoolean("EXTRA_LOGIN_SESSION", true);
                        shEditor.commit();
                        Intent intent = new Intent(createDB.this, Home.class);
                        startActivity(intent);
                        isLogin = false;
                        break;
                    }
                    else{
                        isLogin = true;
                    }

                }
                if(isLogin){
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setSharedPreferenced(){
        shPref = getApplicationContext().getSharedPreferences("EXTRA_DATABASE_SESSION", MODE_PRIVATE);
        shEditor = shPref.edit();
        checkLoginSession();
    }

    private void checkLoginSession()
    {
        if (shPref.getBoolean("EXTRA_LOGIN_SESSION",false)) {
            startActivity(new Intent(createDB.this, Home.class));
            finish();
        }
    }


}
