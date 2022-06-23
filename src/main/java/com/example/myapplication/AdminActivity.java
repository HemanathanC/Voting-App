package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    Connect connect;
    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        connect = new Connect(this);

        b1 = findViewById(R.id.vv);
        b2 = findViewById(R.id.vc);
        b3 = findViewById(R.id.av);
        b4 = findViewById(R.id.ac);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==b1){
            Intent intent = new Intent(this, Voters.class);
            startActivity(intent);
        }
        else if(view==b2){
            Intent intent = new Intent(this, Candidates.class);
            startActivity(intent);
        }
        else if(view==b3){
            Intent intent = new Intent(this, AddVoters.class);
            startActivity(intent);
        }
        else if(view==b4){
            Intent intent = new Intent(this, AddCandidates.class);
            startActivity(intent);
        }
    }
}
