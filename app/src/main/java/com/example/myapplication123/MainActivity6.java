package com.example.myapplication123;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    ImageView image;
    EditText txt1;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Bitmap bitmap;
    Button b,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        openHelper = new imagedb(this);
    }

    public void delete(View view) {
        try {
            txt1=(EditText)findViewById(R.id.id1del);
            String ids=txt1.getText().toString();
            db=openHelper.getWritableDatabase();
            boolean a=deletedata(ids);
            if(a==true) {
                Toast.makeText(getApplicationContext(),
                        "data deleted", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "deleting error", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),
                    "" +e,Toast.LENGTH_LONG).show();

        }
    }

    private boolean deletedata(String ids) {
        long id = db.delete(imagedb.TABLE_NAME,"ID=?",new String[]{String.valueOf(ids)});
        return true;
    }
}