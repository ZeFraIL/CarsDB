package com.zeev.fraiman.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class InsertCar extends AppCompatActivity {

    HelperDB hlp;
    SQLiteDatabase db;
    Button bInsert;
    EditText etCompany, etModel, etYear, etPrice;
    String stCode, stCompany, stModel, stYear, stPrice;
    int caryear;
    double carprice;
    Car car;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_car);

        initComponents();

        bInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCarData();
            }
        });

    }

    private void initComponents() {
        bInsert=findViewById(R.id.bInsert);
        etCompany=findViewById(R.id.etCompany);
        etModel=findViewById(R.id.etModel);
        etYear=findViewById(R.id.etYear);
        etPrice=findViewById(R.id.etPrice);
    }

    public void insertCarData() {
        stCompany=etCompany.getText().toString();
        stModel=etModel.getText().toString();
        stYear=etYear.getText().toString();
        stPrice=etPrice.getText().toString();

        caryear=Integer.parseInt(stYear);
        carprice=Double.parseDouble(stPrice);

        c=Calendar.getInstance();
        stCode=""+c.get(Calendar.YEAR)+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)+
                c.get(Calendar.HOUR_OF_DAY)+c.get(Calendar.MINUTE)+c.get(Calendar.MILLISECOND);




        car=new Car(stCode,stCompany, stModel, caryear,carprice);
        ContentValues contentValues=new ContentValues();
        contentValues.put(hlp.CODE, car.getCode());
        contentValues.put(hlp.COMPANY,car.getCompany());
        contentValues.put(hlp.MODEL, car.getModel());
        contentValues.put(hlp.YEAR, car.getYear());
        contentValues.put(hlp.PRICE, car.getPrice());
        hlp=new HelperDB(getApplicationContext());
        db=hlp.getWritableDatabase();
        db.insert(hlp.TABLE_CARS, null, contentValues);
        db.close();












        Toast.makeText(this, ""+car.toString(), Toast.LENGTH_SHORT).show();

        //hlp.addCar(car);

        etCompany.setText("");
        etModel.setText("");
        etYear.setText("");
        etPrice.setText("");

        Toast.makeText(this, "Ok, new car added", Toast.LENGTH_LONG).show();
    }
}