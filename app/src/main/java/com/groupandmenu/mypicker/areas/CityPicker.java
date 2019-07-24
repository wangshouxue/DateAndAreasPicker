package com.groupandmenu.mypicker.areas;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.groupandmenu.mypicker.datepicker.WheelPicker;

import java.util.ArrayList;
import java.util.List;

public class CityPicker extends WheelPicker<String> {
    private String mSelectedCity;
    private OnCitySelectedListener mOnCitySelectedListener;
    List<String> cityData;
    int index;

    public CityPicker(Context context) {
        this(context, null);
    }

    public CityPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CityPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("0000");
        updateCity();
        setSelectedCity(mSelectedCity, false);
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                mSelectedCity = item;
                if (mOnCitySelectedListener != null) {
                    mOnCitySelectedListener.onCitySelected(item);
                }
            }
        });
    }
    private void updateCity() {
        cityData=new ArrayList<>();
        for (int c = 0; c < ProPicker.jsonBean.get(index).getCityList().size(); c++) {//遍历该省份的所有城市
            String CityName = ProPicker.jsonBean.get(index).getCityList().get(c).getName();
            cityData.add(CityName);
        }
        mSelectedCity=cityData.get(0);
        setDataList(cityData);
    }
    public void setSelectedCity(String mSelectedCity, boolean smoothScroll) {
        setCurrentPosition(cityData.indexOf(mSelectedCity), smoothScroll);
    }
    public void setOnCitySelectedListener(OnCitySelectedListener onCitySelectedListener) {
        this.mOnCitySelectedListener = onCitySelectedListener;
    }

    public String getSelectedCity() {
        return mSelectedCity;
    }

    public void setPro(String pro, int position){
        index=position;
        updateCity();
    }
    public interface OnCitySelectedListener {
        void onCitySelected(String year);
    }


}
