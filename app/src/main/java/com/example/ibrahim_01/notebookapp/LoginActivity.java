package com.example.ibrahim_01.notebookapp;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static  String name_value;

    private static  String password_value;

    private static EditText name;
    private static EditText password;
    DBAdapter db = new DBAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);











        db.open();
//        long id = db.insertContact("admin", "123");

        try {
            Cursor c = db.getAllContacts();
            if	(c.moveToFirst())			{
                do	{
                    DisplayContact(c);
                }	while	(c.moveToNext());
            }
        }
        catch (IndexOutOfBoundsException i)
        {
            Toast.makeText(this,"nothing in there",
                    Toast.LENGTH_LONG).show();
            db.close();}




    }

    public void Login_click(View view)  {


            name = (EditText) findViewById(R.id.editText);
            password = (EditText) findViewById(R.id.editText2);

            name_value = name.getText().toString();
            password_value = password.getText().toString();

            db.open();
            Cursor c	=	db.getContact(name_value,password_value);
            if	(c.moveToFirst()){
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            } else {

                Toast.makeText(getBaseContext(), "No	contact	found", Toast.LENGTH_LONG).show();
                db.close();



            }



    }

    /*public void Login_click(View v) {



        name_value = name.getText().toString();
        //name_value1 =  "\"" + name_value + "\"";
        password_value = password.getText().toString();
        db.open();
        Cursor c = db.getContact(name_value, password_value);
        if (c.moveToFirst()) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        } else {

            Toast.makeText(this, "No	contact	found", Toast.LENGTH_LONG).show();
            db.close();


        }

    }


*/


    public void Register_click(View view) {

            Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(intent);





    }


    public	void	DisplayContact(Cursor	c)

    {
        Toast.makeText(this,"id:	"	+	c.getString(0)	+	"\n"	+
                        "Name:	"	+	c.getString(1)	+	"\n"	+
                        "Password:		"	+	c.getString(2),
                Toast.LENGTH_LONG).show();
    }


   /* public void register_button(View v) {




    }
*/
}