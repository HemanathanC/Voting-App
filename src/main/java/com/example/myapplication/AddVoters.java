package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddVoters extends AppCompatActivity implements View.OnClickListener {

    EditText t1, t2, t3, t4, t5, t6, t7, t8, t9, t10,t11;
    Connect connect;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voters);

        connect = new Connect(this);

        t1=findViewById(R.id.name);
        t2=findViewById(R.id.v_id);
        t3=findViewById(R.id.sex);
        t4=findViewById(R.id.age);
        t5=findViewById(R.id.status);
        t6=findViewById(R.id.city);
        t7=findViewById(R.id.a_no);
        t8=findViewById(R.id.p_no);
        t9=findViewById(R.id.v_status);
        t10=findViewById(R.id.uname);
        t11=findViewById(R.id.pass);

        b=findViewById(R.id.add);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==b){
            if(connect.addvalues(t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),t4.getText().toString(),t5.getText().toString(),t6.getText().toString(),t7.getText().toString(),t8.getText().toString(),t9.getText().toString(),t10.getText().toString(),t11.getText().toString())) {
                Toast.makeText(this, "Voter Added Successfully!!!", Toast.LENGTH_SHORT).show();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                t7.setText("");
                t8.setText("");
                t9.setText("");
                t10.setText("");
                t11.setText("");
            }
        }
    }
}
