package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Connect connect;
    EditText e1,e2;
    Button b;
    RadioGroup rg;
    RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect  = new Connect(this);

        e1=findViewById(R.id.username);
        e2=findViewById(R.id.password);
        b=findViewById(R.id.button);
        rg=findViewById(R.id.radio);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
    }

    private void openActivity() {

        String uname = e1.getText().toString();
        String pass = e2.getText().toString();
        try {
            int id = rg.getCheckedRadioButtonId();
            rb = findViewById(id);
            if (rb.getText().toString().equals("Admin")) {
                if (connect.check_admin(uname, pass)) {
                    Intent intent = new Intent(this, AdminActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Invalid Username and Password!!!", Toast.LENGTH_SHORT).show();
            }
            else if (rb.getText().toString().equals("Voter")){
                if (connect.check(uname, pass)) {

                    connect.setvalues(uname, pass);

                    Intent intent = new Intent(this, FirstActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(this, "Invalid Username and Password!!!", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){e.printStackTrace();}
    }
}