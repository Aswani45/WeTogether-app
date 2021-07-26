package com.example.ganahigana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ganahigana.databinding.ActivitySignupBinding;
import com.example.ganahigana.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class signupActivity extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener firebaseAuthStateListiner;
    ActivitySignupBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog PreogressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        PreogressDialog = new ProgressDialog(signupActivity.this);
        PreogressDialog.setTitle("creating Account");
        PreogressDialog.setMessage("we are creating your account");


        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreogressDialog.show();
                mAuth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(), binding.etPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                PreogressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    if (binding.etEmail == null || binding.etuserName == null || binding.etPassword == null) {
                                        binding.etPassword.requestFocus();
                                        binding.etEmail.requestFocus();
                                        binding.etuserName.requestFocus();
                                        Toast.makeText(signupActivity.this, "Please fill the above", Toast.LENGTH_SHORT).show();
                                        return;

                                    } else {
                                        Users user = new Users(binding.etuserName.getText().toString(), binding.etEmail.getText().toString(),
                                                binding.etPassword.getText().toString());
                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(user);


//                                        FirebaseInstallations.getInstance().getToken(true)
//                                                .addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>() {
//                                                    @Override
//                                                    public void onComplete(@NotNull Task<InstallationTokenResult> task) {
//                                                        if (task.isSuccessful() && task.getResult() != null) {
//                                                            String token = task.getResult().getToken();
//                                                            Toast.makeText(signupActivity.this, token, Toast.LENGTH_SHORT).show();
//                                                            database.getReference("Users").child(mAuth.getCurrentUser().getUid()).child("token").setValue(token);
//                                                        } else {
//
//                                                        }
//                                                    }
//                                                });
                                        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                database.getReference("Users").child(mAuth.getCurrentUser().getUid()).child("token").setValue(s);
                                            }
                                        });


                                        Toast.makeText(signupActivity.this, "user created successfull", Toast.LENGTH_SHORT).show();


                                    }
                                } else {
                                    Toast.makeText(signupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });


        binding.tvAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupActivity.this, signinActivity.class);
                startActivity(intent);

            }
        });


    }


}
