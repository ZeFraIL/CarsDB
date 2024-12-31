package com.zeev.fraiman.carsdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class HelperDB extends SQLiteOpenHelper {

    public static final String DB_FILE="all_cars.db";

    public static final String TABLE_CARS="Cars";
    public static final String CODE="Code";
    public static final String COMPANY="Company";
    public static final String MODEL="Model";
    public static final String YEAR="Year";
    public static final String PRICE="Price";



    public HelperDB(Context context) {
        super(context, DB_FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String st="CREATE TABLE IF NOT EXISTS "+TABLE_CARS+" ( ";
        st+=CODE+" TEXT, "+COMPANY+" TEXT, "+MODEL+" TEXT, "+YEAR+" INTEGER, "+PRICE+" REAL);";
        db.execSQL(st);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCar(Car car)  {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(CODE,car.getCode());
        cv.put(COMPANY,car.getCompany());
        cv.put(MODEL,car.getModel());
        cv.put(YEAR,car.getYear());
        cv.put(PRICE,car.getPrice());
        db.insert(HelperDB.TABLE_CARS, null, cv);
        db.close();
    }

    public ArrayList<Car> allData() {
        ArrayList<Car> arrayList=new ArrayList<Car>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(TABLE_CARS,
                null, null, null,
                null, null, null);
        int n=c.getCount();
        c.moveToFirst();
        for (int i = 0; i < n; i++) {
            Car car=new Car("","","",0,0.0);
            car.setCode(c.getString((int)c.getColumnIndex(CODE)));
            car.setCompany(c.getString((int)c.getColumnIndex(COMPANY)));
            car.setModel(c.getString((int)c.getColumnIndex(MODEL)));
            car.setYear(c.getInt((int)c.getColumnIndex(YEAR)));
            car.setPrice(c.getDouble((int)c.getColumnIndex(PRICE)));
            arrayList.add(car);
            c.moveToNext();
        }
        db.close();
        return arrayList;
    }
}
