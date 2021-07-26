package com.example.ganahigana.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganahigana.Adapters.UserAdapter;
import com.example.ganahigana.R;
import com.example.ganahigana.databinding.ActivitySettingsActivtyBinding;
import com.example.ganahigana.databinding.FragmentCallsBinding;
import com.example.ganahigana.databinding.FragmentChatsBinding;
import com.example.ganahigana.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CallsFragment extends Fragment {

    FragmentCallsBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase  database;
    FirebaseAuth mAuth;



    public CallsFragment() {
        // Required empty public constructor
    }



    }

