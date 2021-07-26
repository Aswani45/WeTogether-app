package com.example.ganahigana;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.ganahigana.Adapters.chatAdapter;
import com.example.ganahigana.databinding.ActivityChatDetailBinding;
import com.example.ganahigana.models.messagesModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {
    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String token;


    @Nullable
    @org.jetbrains.annotations.Nullable


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();
//       token =  FirebaseMessaging.getInstance().getToken().getResult();



        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        final String senderid = mAuth.getUid();
        String recieverid = getIntent().getStringExtra("userid");
        String username = getIntent().getStringExtra("username");
        String profilepic = getIntent().getStringExtra("profilePic");
        binding.username.setText(username);
        Picasso.get().load(profilepic).placeholder(R.drawable.user).into(binding.profileImage);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<messagesModel> messagesModels = new ArrayList<>();

        final chatAdapter chatAdapter = new chatAdapter(messagesModels , this);

        binding.chatrecyclerview.setAdapter(chatAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatrecyclerview.setLayoutManager(layoutManager);

        final String senderRoom = senderid + recieverid;
        final String recieverRoom = recieverid + senderid;

        database.getReference().child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        messagesModels.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren())
                        {
                            messagesModel model = snapshot1
                                    .getValue(messagesModel.class);
                            messagesModels.add(model);
                        }
                        chatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });




        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg =  binding.etmessagetobesend.getText().toString();
                String title = mAuth.getCurrentUser().getDisplayName();
                database.getReference("Users").child(recieverid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                       token = snapshot.getValue(String.class);

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
                final messagesModel model = new messagesModel(senderid,msg);
                model.setTimestamp(new Date().getTime());



                if(msg!=null && title!=null && token!=null)
                {   Toast.makeText(ChatDetailActivity.this, token, Toast.LENGTH_SHORT).show();

                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token,
                            title,msg,getApplicationContext(),ChatDetailActivity.this);
                    notificationsSender.SendNotifications();
                    Toast.makeText(ChatDetailActivity.this, "notification send", Toast.LENGTH_SHORT).show();
                }


                binding.etmessagetobesend.setText("");


                database.getReference().child("chats")
                        .child(senderRoom)
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        database.getReference().child("chats")
                                .child(recieverRoom)
                                .push()
                                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }

                        });
                    }
                });


            }
        });






    }
}