package com.example.ganahigana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganahigana.Adapters.FragmentsAdapters;
import com.example.ganahigana.Adapters.chatAdapter;

import com.example.ganahigana.databinding.ActivitySelectionactivityBinding;
import com.example.ganahigana.models.messagesModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class selectionactivity extends AppCompatActivity {
    ActivitySelectionactivityBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySelectionactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseMessaging.getInstance().subscribeToTopic("all");

//        binding.viewpager1.setAdapter(new FragmentsAdapters(getSupportFragmentManager()));
//        binding.tablayout.setupWithViewPager(binding.viewpager1);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();






        final String sender = mAuth.getUid();









        setContentView(binding.getRoot());



//        Intent intent = getIntent();
//        Bundle args = intent.getBundleExtra("BUNDLE");
//        ArrayList<String> arraylist = (ArrayList<String>) args.getSerializable("ARRAYLIST");




//        String recieverid = getIntent().getStringExtra("userid");
//        String username = getIntent().getStringExtra("username");
//        String profilepic = getIntent().getStringExtra("profilePic");
//        binding.username.setText(username);
//        Picasso.get().load(profilepic).placeholder(R.drawable.user).into(binding.profileImage);
        binding.bttnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectionactivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


//
//        final chatAdapter chatAdapter = new chatAdapter(messagesModels , this);
//
//        binding.chatrecyclerview.setAdapter(chatAdapter);
//
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        binding.chatrecyclerview.setLayoutManager(layoutManager);







        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg =  binding.itemsToBEsend.getText().toString();

                String title = mAuth.getCurrentUser().getDisplayName();


                final messagesModel model = new messagesModel(sender,msg);
                model.setTimestamp(new Date().getTime());

                binding.itemsToBEsend.setText("");



                if(title!=null && msg != null)
                {
                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",
                            title,msg,getApplicationContext(),selectionactivity.this);
                    notificationsSender.SendNotifications();

                }
                else
                {
                    Toast.makeText(selectionactivity.this, "enter msg", Toast.LENGTH_SHORT).show();
                }





            }
        });



    }



            }
