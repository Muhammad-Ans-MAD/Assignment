package com.example.myapplication123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
Button btn;
EditText editText1, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final SQLiteDatabase db;
        SQLiteOpenHelper openHelper;
        final Cursor[] cursor = new Cursor[1];
        openHelper = new database(this);
        db = openHelper.getReadableDatabase();
        editText1 = (EditText) findViewById(R.id.useremailL);
        editText2 = (EditText) findViewById(R.id.userpasswordL);
        btn=(Button)findViewById(R.id.buttonL);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText1.getText().toString();
                String pass = editText2.getText().toString();
                cursor[0] = db.rawQuery("SELECT *FROM " + database.TABLE_NAME +
                        " WHERE " + database.COL_3 + "=? AND "
                        + database.COL_4 + "=?", new String[]{email, pass});
                if (cursor[0] != null) {
                    if (cursor[0].getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity4.this,MainActivity5.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}