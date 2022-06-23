package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.SQLException;

public class Profile extends Fragment {

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        t1=view.findViewById(R.id.name);
        t1.setText(Values.getName());
        t2=view.findViewById(R.id.v_id);
        t2.setText(Values.getV_id());
        t3=view.findViewById(R.id.sex);
        t3.setText(Values.getSex());
        t4=view.findViewById(R.id.age);
        t4.setText(Values.getAge());
        t5=view.findViewById(R.id.status);
        t5.setText(Values.getStatus());
        t6=view.findViewById(R.id.city);
        t6.setText(Values.getCity());
        t7=view.findViewById(R.id.a_no);
        t7.setText(Values.getA_no());
        t8=view.findViewById(R.id.p_no);
        t8.setText(Values.getP_no());
        t9=view.findViewById(R.id.v_status);
        t9.setText(Values.getV_status());

        return view;    }
}
