package com.zeev.fraiman.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Junction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junction);
        if (savedInstanceState != null) {
            String value = savedInstanceState.getString("key");
            int count = savedInstanceState.getInt("count");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "value");
        outState.putInt("count", 10);
    }

    public void goInsert(View view) {
        Intent go=new Intent(getApplicationContext(), InsertCar.class);
        startActivity(go);
    }

    public void goView(View view) {
        Intent go=new Intent(getApplicationContext(), ViewData.class);
        startActivity(go);
    }

    public void goUpdate(View view) {
        Intent go=new Intent(getApplicationContext(), UpdateData.class);
        startActivity(go);
    }
}