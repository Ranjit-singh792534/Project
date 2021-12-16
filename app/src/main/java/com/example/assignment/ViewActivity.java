package com.example.assignment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<String> contactName,Phoneno,Email;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recyclerView);
        myDB = new DatabaseHelper(ViewActivity.this);

        contactName = new ArrayList<>();
        Phoneno = new ArrayList<>();
        Email = new ArrayList<>();
        storeDataInArrays();
    customAdapter = new CustomAdapter(ViewActivity.this,contactName,Phoneno,Email);
    recyclerView.setAdapter(customAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));

    }
    //store data in Arrays
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() ==0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                contactName.add(cursor.getString(1));
                Phoneno.add(cursor.getString(2));
                Email.add(cursor.getString(3));
            }
        }
    }
}
