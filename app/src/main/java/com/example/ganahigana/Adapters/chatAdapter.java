package com.example.ganahigana.Adapters;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ganahigana.R;
import com.example.ganahigana.models.messagesModel;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class chatAdapter extends RecyclerView.Adapter{
    ArrayList<messagesModel> messagesModels;
    Context context;
    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;


    public chatAdapter(ArrayList<messagesModel> messagesModels, Context context) {
        this.messagesModels = messagesModels;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }

       else
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
            return new RecieverViewHolder(view);
        }

    }


    @Override
    public int getItemViewType(int position) {
        if(messagesModels.get(position).getuID().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }
        else
        {
            return RECIEVER_VIEW_TYPE;
        }



    }


    @Override


    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        messagesModel messagesModel = messagesModels.get(position);

        if(holder.getClass() == SenderViewHolder.class)
        {
            ((SenderViewHolder)holder).senderMsg.setText(messagesModel.getMessage());
            Date newDate = new Date(messagesModel.getTimestamp());
            newDate.getTime();
            ((SenderViewHolder)holder).sendertime.setText(newDate.toString().substring(10,16));


        }
        else
        {
            ((RecieverViewHolder)holder).recieverMsg.setText(messagesModel.getMessage());
            Date newDate = new Date(messagesModel.getTimestamp());
            newDate.getTime();

            ((RecieverViewHolder)holder).recievertime.setText(newDate.toString().substring(10,16));


        }

    }







    @Override
    public int getItemCount() {
        return messagesModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {
        TextView recieverMsg , recievertime;
        public RecieverViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            recieverMsg = itemView.findViewById(R.id.message_recieve);
            recievertime=itemView.findViewById(R.id.recieverTime);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder
    {
        TextView senderMsg , sendertime;
        public SenderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.msgsend);
            sendertime = itemView.findViewById(R.id.timesender);
        }

    }



}
