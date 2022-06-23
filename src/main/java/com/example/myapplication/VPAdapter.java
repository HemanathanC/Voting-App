package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"Profile","Cast Vote","Result"};

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:return new Profile();
            case 1:return new Vote();
            case 2:return new Result();
        }
        return new Profile();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
