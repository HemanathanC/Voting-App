package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.ResultSet;

public class Result extends Fragment {

    TextView t1,res;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_result, container, false);

        FrameLayout main=view.findViewById(R.id.result);
        res=view.findViewById(R.id.name);

        Connect connect = new Connect(getContext());

        try {
            Cursor cursor1 = connect.result();
            cursor1.moveToNext();
            res.setText("\""+cursor1.getString(0)+"\"");
            res.setTextSize(30);
            res.setGravity(Gravity.CENTER);
            main.addView(res);
        }
        catch(Exception e){e.printStackTrace();}

        return view;
    }
}
