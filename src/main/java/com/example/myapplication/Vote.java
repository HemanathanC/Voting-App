package com.example.myapplication;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Vote extends Fragment{

    Button b;
    EditText e1,e2;
    RadioGroup rg;
    RadioButton[] rb;
    String sym;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_vote, container, false);

        Connect connect = new Connect(getContext());

        e1=view.findViewById(R.id.v_id);
        e2=view.findViewById(R.id.v_aadhar);

        ArrayList<String> symbols=new ArrayList<>();
        try
        {
            Cursor cursor = connect.getSymbols();
            while(cursor.moveToNext())
                symbols.add(cursor.getString(1));
        }catch (Exception e) { e.printStackTrace(); }

        b=view.findViewById(R.id.button);

        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, symbols);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                sym=parent.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(connect.checkvoted(e1.getText().toString(),e2.getText().toString())){
                        Toast.makeText(getContext(), "You had already voted!!!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        if(connect.update_v_status(e1.getText().toString(),sym))
                            Toast.makeText(getContext(), "You voted Successfully!!!", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e1) {e1.printStackTrace();}
            }
        });
        return view;
    }
}
