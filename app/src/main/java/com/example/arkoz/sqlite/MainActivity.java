package com.example.arkoz.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper mydata;
    EditText txtName, txtPhone, txtEmail, txtId;
    Spinner txtCountry, txtState;
    Button btnClick, btnRead, btnUpdate, btnDelete;
    TextView txtResult;

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
        btnRead = (Button) findViewById(R.id.btn_read);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        txtResult = (TextView) findViewById(R.id.idResult);
        txtId = (EditText) findViewById(R.id.txt_id);

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

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseRead();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUpdate();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseDelete();
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

    private void DatabaseRead(){
        Cursor result = mydata.getData();
        StringBuffer stringBuffer = new StringBuffer();
        if (result != null && result.getCount() > 0) {
            while (result.moveToNext()) {
                stringBuffer.append("Id:"+result.getString(0)+'\n');
                stringBuffer.append("Name:"+result.getString(1)+'\n');
                stringBuffer.append("Phone:"+result.getString(2)+'\n');
                stringBuffer.append("Email:"+result.getString(3)+'\n');
                stringBuffer.append("Country:"+result.getString(4)+'\n');
                stringBuffer.append("State:"+result.getString(5)+'\n');
            }
            txtResult.setText(stringBuffer.toString());
            Toast.makeText(this, "Data Reieved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
        }
    }

    private void DatabaseUpdate() {
        String id = txtId.getText().toString();
        String name = txtName.getText().toString();
        String phone = txtPhone.getText().toString();
        String email = txtEmail.getText().toString();
        String country = txtCountry.getSelectedItem().toString();
        String state = txtState.getSelectedItem().toString();

        Boolean res = mydata.updateData(id, name, phone, email, country, state);
        if (res == true)
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data Insertion Failure", Toast.LENGTH_SHORT).show();
    }

    private void DatabaseDelete() {
        String id = txtId.getText().toString();
        int result = mydata.deleteData(id);
        Toast.makeText(this, result+":Rows Affected", Toast.LENGTH_SHORT).show();
    }
}
