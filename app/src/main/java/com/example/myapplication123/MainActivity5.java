package com.example.myapplication123;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity5 extends AppCompatActivity {
    ImageView image;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Bitmap bitmap;
    Button b,b2,b3,b5,b9;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        openHelper = new imagedb(this);
        t1=(EditText)findViewById(R.id.loc_name);
        t2=(EditText)findViewById(R.id.loc_description);
        image=(ImageView)findViewById(R.id.selectimg);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

        b=(Button)findViewById(R.id.save_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name=t1.getText().toString();
                    String desc=t2.getText().toString();
                    image.setDrawingCacheEnabled(true);
                    image.buildDrawingCache();
                    Bitmap bitmap2=image.getDrawingCache();
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] data=baos.toByteArray();
                    db=openHelper.getWritableDatabase();
                    insertdata(data,name,desc);
                    Toast.makeText(getApplicationContext(),
                            "Data saved" ,Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),
                            "" +e,Toast.LENGTH_LONG).show();

                }
            }
        });
    b5=(Button)findViewById(R.id.buttondelete);
    b5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity5.this,MainActivity6.class);
            startActivity(intent);
        }
    });
    b9=(Button)findViewById(R.id.buttonUpdate);
    b9.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity5.this,MainActivity8.class);
            startActivity(intent);
        }
    });
    }

    private void insertdata(byte[] data, String name, String desc) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(imagedb.COL_2, data);
        contentValues.put(imagedb.COL_3, name);
        contentValues.put(imagedb.COL_4, desc);
        try{
        long id = db.insert(imagedb.TABLE_NAME, null, contentValues);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),
                    "" +e,Toast.LENGTH_LONG).show();
        }
        //return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            try{
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),
                        "Error in Bitmap",Toast.LENGTH_LONG).show();
            }
            image.setImageBitmap(bitmap);
        }

        else{
            Toast.makeText(getApplicationContext(),
                    "Error in If clause",Toast.LENGTH_LONG).show();
        }
    }
}