package com.kiellenaidu.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button btnLogin;
    Button btnSignup;
    EditText edtUsername;
    EditText edtPassword;
    FirebaseAuth Auth;
    private String TAG = "Login Feedback";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // ...
// Initialize Firebase Auth
        Auth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String Password = edtPassword.getText().toString();
                Auth = FirebaseAuth.getInstance();
                if (!username.isEmpty() && !Password.isEmpty()) {
                    Auth.signInWithEmailAndPassword(username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Login.this, "Logging in", Toast.LENGTH_SHORT).show();
                                Intent loggedInUser = new Intent(Login.this, MainActivity.class);
                                startActivity(loggedInUser);
                            }else {
                                Toast.makeText(Login.this, "Whoops", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(Login.this, "Please full in the required fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String Password = edtPassword.getText().toString();
                Auth = FirebaseAuth.getInstance();

                if (!username.isEmpty() && !Password.isEmpty()) {
                    Auth.createUserWithEmailAndPassword(username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "User logged in", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "Whoops", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


}

