package com.example.litepaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.LitePalSupport;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG= "LitePal";
    private TextView tv;
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
        tv = (TextView)findViewById(R.id.text);
        Button createdatabase = (Button) findViewById(R.id.create_database);
        createdatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        Button adddata = (Button)findViewById(R.id.add_data);
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code 2");
                book.setAuthor("Dan Brown");
                book.setPages(453);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.setId(id);
                book.save();
                Log.d(TAG,"数据添加成功 ");
                id = id + 1;
            }
        });
        Button querydata = (Button) findViewById(R.id.query_data);
        querydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for(Book book :books){
                    Log.d(TAG,"book name is "+book.getName());
                    Log.d(TAG,"book author is "+book.getAuthor());
                    Log.d(TAG,"book pages is "+book.getPages());
                    Log.d(TAG,"book price is "+book.getPrice());
                    Log.d(TAG,"book press is "+book.getPress());
                    Log.d(TAG,"book Id is "+book.getId());
                }
                Book firstbook = LitePal.findFirst(Book.class);
                Log.d(TAG,"firstbook name is "+firstbook.getName());
                Log.d(TAG,"firstbook author is "+firstbook.getAuthor());
                Log.d(TAG,"firstbook pages is "+firstbook.getPages());
                Log.d(TAG,"firstbook price is "+firstbook.getPrice());
                Log.d(TAG,"firstbook press is "+firstbook.getPress());
                Log.d(TAG,"firstbook Id is "+firstbook.getId());
                Book lastbook = LitePal.findLast(Book.class);
                tv.setText(lastbook.getName());

            }
        });
    }
}
