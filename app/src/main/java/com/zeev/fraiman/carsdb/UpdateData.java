package com.zeev.fraiman.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class UpdateData extends AppCompatActivity {

    RadioButton rbCompany, rbModel, rbYear, rbPrice;
    EditText etOld, etNew;
    Button bUpdate;
    String stOld="", stNew="", forUpdate="", fieldUpdate="";
    HelperDB hlp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        initElements();

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stOld=etOld.getText().toString();
                stNew=etNew.getText().toString();

                if (rbCompany.isChecked()) {
                    forUpdate = HelperDB.COMPANY + "=?";
                    fieldUpdate=HelperDB.COMPANY;
                }
                if (rbModel.isChecked()) {
                    forUpdate = HelperDB.MODEL + "=?";
                    fieldUpdate=HelperDB.MODEL;
                }
                if (rbYear.isChecked()) {
                    forUpdate = HelperDB.YEAR + "=?";
                    fieldUpdate=HelperDB.YEAR;
                }
                if (rbPrice.isChecked()) {
                    forUpdate = HelperDB.PRICE + "=?";
                    fieldUpdate=HelperDB.PRICE;
                }

                ContentValues cv=new ContentValues();
                cv.put(fieldUpdate,stNew);

                String[] whatUpdate=new String[]{stOld};

                db=hlp.getWritableDatabase();
                db.update(HelperDB.TABLE_CARS, cv, forUpdate, whatUpdate);
                db.close();

                etNew.setText("");
                etOld.setText("");
            }
        });
    }

    private void initElements() {
        rbCompany=findViewById(R.id.rbCompany);
        rbModel=findViewById(R.id.rbModel);
        rbYear=findViewById(R.id.rbYear);
        rbPrice=findViewById(R.id.rbPrice);
        etOld=findViewById(R.id.etOld);
        etNew=findViewById(R.id.etNew);
        bUpdate=findViewById(R.id.bUpdate);
        hlp=new HelperDB(UpdateData.this);
    }
}