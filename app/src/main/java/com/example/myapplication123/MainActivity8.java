package com.example.myapplication123;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

public class MainActivity8 extends AppCompatActivity {
EditText t1,t2,t3;
    ImageView image;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Bitmap bitmap;
    Button b,b2,b3,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        t1=(EditText)findViewById(R.id.id1upCheck);
        t2=(EditText)findViewById(R.id.loc_names);
        t3=(EditText)findViewById(R.id.loc_descriptions);

        final SQLiteDatabase db;
        SQLiteOpenHelper openHelper;
        Cursor[] cursor = new Cursor[1];
        openHelper = new imagedb(this);
        db = openHelper.getReadableDatabase();
        b=(Button)findViewById(R.id.Upcheck);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=t1.getText().toString();
                cursor[0] = db.rawQuery("SELECT *FROM " + imagedb.TABLE_NAME +
                " WHERE " + imagedb.COL_3 + "=?", new String[]{name});
                if (cursor[0] != null) {
                    if (cursor[0].getCount() > 0) {
//                        Toast.makeText(getApplicationContext(), "Data Found", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(MainActivity8.this,MainActivity9.class);
//                        startActivity(intent);
                        try{

                            while (cursor[0].moveToNext()){
//                            Toast.makeText(getApplicationContext(), ""+cursor[0].getString(3), Toast.LENGTH_SHORT).show();
                                t2.setText(cursor[0].getString(2));
                                t3.setText(cursor[0].getString(3));

                            }}
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        image=(ImageView)findViewById(R.id.selectimgs);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });
    }

    public void update_data(View view) {
        try{
        String first=t2.getText().toString();
        String second=t3.getText().toString();
        String key=t1.getText().toString();
        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache();
        Bitmap bitmap2=image.getDrawingCache();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] data=baos.toByteArray();
        db=openHelper.getWritableDatabase();
        insertdata(data,first,second,key);
        Toast.makeText(getApplicationContext(),
                "Data saved" ,Toast.LENGTH_LONG).show();}
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),
                    ""+e ,Toast.LENGTH_LONG).show();
        }
    }

    private void insertdata(byte[] data, String first, String second, String key) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(imagedb.COL_2, data);
            contentValues.put(imagedb.COL_3, first);
            contentValues.put(imagedb.COL_4, second);
            long id = db.update(imagedb.TABLE_NAME, contentValues, "Location_Name=?", new String[]{String.valueOf(key)});
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),
                    ""+e ,Toast.LENGTH_LONG).show();
        }

    }


    //    public void Update(View view) {
//        String id=t1.getText().toString();
//        cursor[0] = db.rawQuery("SELECT *FROM " + database.TABLE_NAME +
//                " WHERE " + database.COL_3 + "=? AND "
//                + database.COL_4 + "=?", new String[]{email, pass});
//    }
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