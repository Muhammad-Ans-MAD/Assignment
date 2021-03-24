package com.example.myapplication123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
Button btn;
    EditText editText1, editText2, editText3;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        openHelper = new database(this);
        editText1 = (EditText)findViewById(R.id.usernameS);
        editText2 = (EditText)findViewById(R.id.useremailS);
        editText3 = (EditText)findViewById(R.id.userpasswordS);
        btn=(Button)findViewById(R.id.buttonSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String name=editText1.getText().toString();
                String email=editText2.getText().toString();
                String pass=editText3.getText().toString();
                insertdata(name, email,pass);
                Toast.makeText(getApplicationContext(),
                        "register successfully",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insertdata(String name, String email, String pass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.COL_2, name);
        contentValues.put(database.COL_3, email);
        contentValues.put(database.COL_4, pass);
        long id = db.insert(database.TABLE_NAME, null, contentValues);
    }
}