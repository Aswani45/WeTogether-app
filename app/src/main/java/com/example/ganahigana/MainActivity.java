package com.example.ganahigana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ganahigana.Adapters.FragmentsAdapters;
import com.example.ganahigana.databinding.ActivityMainBinding;
import com.example.ganahigana.databinding.ActivitySigninBinding;
import com.example.ganahigana.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity  {


        ActivityMainBinding binding;
        FirebaseAuth  mAuth;

        FirebaseDatabase database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.viewpager.setAdapter(new FragmentsAdapters(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewpager);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.setting:
                Intent intentt = new Intent(MainActivity.this , settingsActivty.class);
                startActivity(intentt);


                break;
            case R.id.logout:
                mAuth.signOut();
                Intent intent =new Intent(MainActivity.this , signupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            case R.id.select:
                Intent intenttt =new Intent(MainActivity.this , selectionactivity.class);
                intenttt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intenttt);
                break;





        }

        return true;
    }






}
