package com.groupandmenu.mypicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.groupandmenu.mypicker.areas.AddressPickerDialogFragment;
import com.groupandmenu.mypicker.datepicker.date.DatePickerDialogFragment;
import com.groupandmenu.mypicker.datepicker.time.TimePickerDialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
                datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
                    @Override
                    public void onDateChoose(int year, int month, int day) {
                        Toast.makeText(MainActivity.this, year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
                    }
                });
                datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");

            }
        });
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialogFragment datePickerDialogFragment = new TimePickerDialogFragment();
                datePickerDialogFragment.setOnDateChooseListener(new TimePickerDialogFragment.OnDateChooseListener() {
                    @Override
                    public void onDateChoose(int year, int month, int day, int hour, int minute) {
                        Toast.makeText(MainActivity.this, year+"-"+month+"-"+day+"-"+hour+"-"+minute, Toast.LENGTH_SHORT).show();
                    }
                });
                datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");

            }
        });
        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressPickerDialogFragment pickerDialogFragment = new AddressPickerDialogFragment();
                pickerDialogFragment.setOnAddressChooseListener(new AddressPickerDialogFragment.OnAddressChooseListener() {
                    @Override
                    public void onAddressChoose(String pro, String city) {
                        Toast.makeText(MainActivity.this, pro + "-" + city , Toast.LENGTH_SHORT).show();
                    }
                });
                pickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");

            }
        });
    }
}
