package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCandidates extends AppCompatActivity implements View.OnClickListener {

    Connect connect;
    Button b;
    EditText e1,e2,e3,e4,e5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);

        connect = new Connect(this);

        e1=findViewById(R.id.name);
        e2=findViewById(R.id.symbol);
        e3=findViewById(R.id.sex);
        e4=findViewById(R.id.age);
        e5=findViewById(R.id.city);

        b=findViewById(R.id.add);
        b.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view==b){
            if(connect.c_addvalues(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString())) {
                Toast.makeText(this, "Candidate Added Successfully!!!", Toast.LENGTH_SHORT).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
            }
        }
    }
}
