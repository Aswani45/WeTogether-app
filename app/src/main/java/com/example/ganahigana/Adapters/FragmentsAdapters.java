package com.example.ganahigana.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ganahigana.Fragments.CallsFragment;
import com.example.ganahigana.Fragments.ChatsFragment;
import com.example.ganahigana.Fragments.StatusFragment;

import org.jetbrains.annotations.NotNull;

public class FragmentsAdapters extends FragmentPagerAdapter {
    public FragmentsAdapters(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    public FragmentsAdapters(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return  new ChatsFragment();
            case 1: return new StatusFragment();
            case 2: return  new CallsFragment();
            default: return new ChatsFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0)
        {
            title = "chats";

        }
        if(position == 1)
        {
            title = "POSTS";

        }
        if(position == 2)
        {
            title = "Calls";

        }

        return title;
    }
}
