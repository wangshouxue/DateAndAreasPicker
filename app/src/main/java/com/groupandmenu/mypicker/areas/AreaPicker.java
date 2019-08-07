package com.groupandmenu.mypicker.areas;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.groupandmenu.mypicker.datepicker.WheelPicker;

import java.util.ArrayList;
import java.util.List;

public class AreaPicker extends WheelPicker<String> {
    private String mSelectedArea;
    private OnAreaSelectedListener mOnAreaSelectedListener;
    List<String> areaData;
    int proIndex,cityIndex;

    public AreaPicker(Context context) {
        this(context, null);
    }

    public AreaPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AreaPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("0000");
        updateCity();
        setSelectedCity(mSelectedArea, false);
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                mSelectedArea = item;
                if (mOnAreaSelectedListener != null) {
                    mOnAreaSelectedListener.onAreaSelected(position,item);
                }
            }
        });
    }
    private void updateCity() {
        areaData=new ArrayList<>();
        for (int c = 0; c < ProPicker.jsonBean.get(proIndex).getCityList().get(cityIndex).getArea().size(); c++) {//遍历该省份的所有城市
            String areaName = ProPicker.jsonBean.get(proIndex).getCityList().get(cityIndex).getArea().get(c);
            areaData.add(areaName);
        }
        mSelectedArea=areaData.get(0);
        setDataList(areaData);
    }
    public void setSelectedCity(String mSelectedCity, boolean smoothScroll) {
        setCurrentPosition(areaData.indexOf(mSelectedCity), smoothScroll);
    }
    public void setOnCitySelectedListener(OnAreaSelectedListener onCitySelectedListener) {
        this.mOnAreaSelectedListener = onCitySelectedListener;
    }

    public String getSelectedArea() {
        return mSelectedArea;
    }

    public void setCity(int proPos, int cityPos){
        proIndex=proPos;
        cityIndex=cityPos;
        updateCity();
    }
    public interface OnAreaSelectedListener {
        void onAreaSelected(int pos, String year);
    }
}
