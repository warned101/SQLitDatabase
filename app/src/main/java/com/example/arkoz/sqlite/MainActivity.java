package com.example.arkoz.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper mydata;
    EditText txtName, txtPhone, txtEmail;
    Spinner txtCountry, txtState;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydata = new DataBaseHelper(this);
        txtName = (EditText) findViewById(R.id.txt_name);
        txtPhone = (EditText) findViewById(R.id.txt_phone);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtCountry = (Spinner) findViewById(R.id.spinner_country);
        txtState = (Spinner) findViewById(R.id.spinner_state);
        btnClick = (Button) findViewById(R.id.idBtn);

        String[] countries = {"India", "Australia", "America"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, countries);
        txtCountry.setAdapter(adapter);

        String[] states = {"Delhi", "UP", "UK", "Sydney","Perth", "Melbourne","Texas","California"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, states);
        txtState.setAdapter(adapter1);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseCall();
            }
        });
    }

    private void DatabaseCall() {
        String name = txtName.getText().toString();
        String phone = txtPhone.getText().toString();
        String email = txtEmail.getText().toString();
        String country = txtCountry.getSelectedItem().toString();
        String state = txtState.getSelectedItem().toString();

        Boolean res = mydata.insertData(name, phone, email, country, state);
        if (res == true)
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data Insertion Failure", Toast.LENGTH_SHORT).show();
    }
}
