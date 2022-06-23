package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FirstActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    private final String[] titles = new String[]{"Profile","Cast Vote","Result"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getSupportActionBar().hide();

        tabLayout=findViewById(R.id.tablelayout);
        viewPager2=findViewById(R.id.viewpager);

        VPAdapter vpAdapter=new VPAdapter(this);
        viewPager2.setAdapter(vpAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
    }
}
