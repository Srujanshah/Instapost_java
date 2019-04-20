package com.example.instapost_java;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instapost_java.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText userEmailEditText, userPassowrdEditText;
    Button loginBtn, registerBtn;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign ID
        userEmailEditText = findViewById(R.id.input_email);
        userPassowrdEditText = findViewById(R.id.input_password);

        loginBtn = findViewById(R.id.btn_login);
        registerBtn = findViewById(R.id.btn_signup);

        // Firebase Authentication Instances
        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Intent moveToHome = new Intent(MainActivity.this,
                            InstaActivity.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListner);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListner);
    }

    private void loginUser() {
        String userEmail, userPassword;

        userEmail = userEmailEditText.getText().toString().trim();
        userPassword = userPassowrdEditText.getText().toString().trim();

        if ( !TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
            mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if ( task.isSuccessful()) {
                        Intent moveToHome = new Intent(MainActivity.this,
                                InstaActivity.class);
                        moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToHome);
                    } else {
                        Toast.makeText(MainActivity.this, "Unable to login user",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
