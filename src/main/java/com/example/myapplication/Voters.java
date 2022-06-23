package com.example.myapplication;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Voters extends AppCompatActivity{

    TableLayout tb;
    TableRow tr;
    TextView[] t;
    TextView title;
    Button b;
    Connect connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        connect = new Connect(this);

        title=findViewById(R.id.title);
        title.setText("ALL VOTERS");
        tb=findViewById(R.id.table);
        t=new TextView[4];

        try{
            SQLiteDatabase db = connect.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * from voters", null);

            tr=new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tr.setLayoutParams(layoutParams);
            tr.setBackgroundColor(Color.CYAN);
            t=new TextView[5];

            t[0]=new TextView(this);
            t[1]=new TextView(this);
            t[2]=new TextView(this);
            t[3]=new TextView(this);
            t[4]=new TextView(this);
            t[0].setText("ID");
            t[1].setText("NAME");
            t[2].setText("AGE");
            t[3].setText("CITY");
            t[4].setText("ACTION");
            for(int i=0;i<5;i++) {
                t[i].setTextAppearance(this,R.style.boldText);
                t[i].setTextColor(Color.BLACK);
                tr.addView(t[i]);
            }
            tb.addView(tr);

            while(cursor.moveToNext()){

                tr=new TableRow(this);
                tr.setLayoutParams(layoutParams);
                t[0]=new TextView(this);
                t[1]=new TextView(this);
                t[2]=new TextView(this);
                t[3]=new TextView(this);
                t[0].setText(cursor.getString(0));
                t[1].setText(cursor.getString(1));
                t[2].setText(cursor.getString(3));
                t[3].setText(cursor.getString(5));
                String val= cursor.getString(0);
                for(int i=0;i<4;i++) {
                    t[i].setTextSize(10);
                    t[i].setTextColor(Color.BLACK);
                    tr.addView(t[i]);
                }
                    b=new Button(this);
                    b.setText("Delete");
                    b.setTextSize(10);
                    b.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    tr.addView(b);
                    tb.addView(tr);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                            alert.setIcon(android.R.drawable.ic_dialog_alert)
                                    .setTitle("Delete Voter").setMessage("Are you sure you want to delete the voter?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(connect.v_delete(val)) {
                                                Toast.makeText(view.getContext(), "Voter Deleted Successfully!!!", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        }
                                    }).setNegativeButton("No", null).show();
                        }
                    });
            }
        }catch (Exception e){e.printStackTrace();}
    }

}
