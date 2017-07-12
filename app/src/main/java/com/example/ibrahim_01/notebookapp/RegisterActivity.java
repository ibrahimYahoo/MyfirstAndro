package com.example.ibrahim_01.notebookapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ibrahim_01.notebookapp.DBAdapter;
import com.example.ibrahim_01.notebookapp.R;

public class RegisterActivity extends AppCompatActivity {

    private  static EditText name;
    private  static  EditText password;
    private  static  EditText address;



    //private  static  EditText name_value = "";
    // private  static  EditText password_value = "";

    DBAdapter db = new DBAdapter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.reg_username);
        password = (EditText)findViewById(R.id.reg_password);
        address = (EditText)findViewById(R.id.address);






    }

    public void  Create_user(View view) {

            db.open();
            long id = db.insertContact(name.getText().toString(), password.getText().toString(),address.getText().toString());
            db.close();



        }
    }


