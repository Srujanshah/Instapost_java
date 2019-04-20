package com.example.instapost_java;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText userEmailEditText, userPassowrdEditText, nameEditText, nicknameEditText;
    Button registerBtn;

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Assign ID
        userEmailEditText = findViewById(R.id.register_email);
        userPassowrdEditText = findViewById(R.id.register_password);
        nameEditText = findViewById(R.id.input_name);
        nicknameEditText = findViewById(R.id.input_nickname);

        registerBtn = findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String userEmail, userPassword, userName, userNickname;

        userEmail = userEmailEditText.getText().toString().trim();
        userPassword = userPassowrdEditText.getText().toString().trim();
        userName = nameEditText.getText().toString().trim();
        userNickname = nicknameEditText.getText().toString().trim();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Please enter password with at least 6 characters",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(userNickname)) {
            Toast.makeText(this, "Please enter your nick name", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(userName, userEmail, userNickname,FirebaseAuth.getInstance().getUid());
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this,
                                                "Registeration database successful!",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this,
                                                "Registeration database failed!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Registeration auth failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}