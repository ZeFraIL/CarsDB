package com.zeev.fraiman.carsdb;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {

    HelperDB helperDB;
    SQLiteDatabase db;

    ListView lvCars;
    Car car;
    ArrayList<Car> alCars;
    ArrayList<String> info;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        lvCars=findViewById(R.id.lvCars);
    }

    public void viewAll(View view) {
        alCars=new ArrayList<>();
        helperDB=new HelperDB(getApplicationContext());
        info=new ArrayList<>();
        db=helperDB.getReadableDatabase();
        Cursor cursor=db.query(HelperDB.TABLE_CARS,null,null,null,
                null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())  {
            int indexCompany=cursor.getColumnIndex(helperDB.COMPANY);
            int indexModel=cursor.getColumnIndex(helperDB.MODEL);
            int indexCode=cursor.getColumnIndex(helperDB.CODE);
            int indexPrice=cursor.getColumnIndex(helperDB.PRICE);
            int indexYear=cursor.getColumnIndex(helperDB.YEAR);
            car=new Car("","","",0,0);
            car.setCode(cursor.getString(indexCode));
            car.setCompany(cursor.getString(indexCompany));
            car.setModel(cursor.getString(indexModel));
            car.setYear(cursor.getInt(indexYear));
            car.setPrice(cursor.getInt(indexPrice));
            alCars.add(car);
            info.add(car.getCode());
            cursor.moveToNext();
        }
        db.close();
        adapter=new ArrayAdapter<>(ViewData.this,
                android.R.layout.simple_list_item_1,
                info);
        lvCars.setAdapter(adapter);

        for (int i = 0; i < alCars.size(); i++) {
            info.add(alCars.get(i).toString());
        }
        adapter=new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, info);
        lvCars.setAdapter(adapter);

        lvCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ViewData.this);
                adb.setTitle("Your data:");
                adb.setMessage(alCars.get(position).toString());
                adb.setPositiveButton("Update this item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.setNegativeButton("Delete this item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.create().show();
            }
        });
    }

    public void byCompany(View view) {
        AlertDialog.Builder adb=new AlertDialog.Builder(getApplicationContext());
        adb.setTitle("Write company by query");
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
          //      String st=et.getText().toString();
            }
        });
        adb.create().show();
    }
}