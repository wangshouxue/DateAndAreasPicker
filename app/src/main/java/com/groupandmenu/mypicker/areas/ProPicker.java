package com.groupandmenu.mypicker.areas;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.groupandmenu.mypicker.JsonBean;
import com.groupandmenu.mypicker.datepicker.WheelPicker;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProPicker extends WheelPicker<String> {
    private String mSelectedPro;
    private OnProSelectedListener mOnProSelectedListener;
    List<String> list;
    public static ArrayList<JsonBean> jsonBean;

    public ProPicker(Context context) {
        this(context, null);
    }

    public ProPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("0000");
        updatePro(context);
        mSelectedPro=list.get(0);
        setSelectedPro(mSelectedPro, false);
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                mSelectedPro = item;
                if (mOnProSelectedListener != null) {
                    mOnProSelectedListener.onProSelected(item,position);
                }
            }
        });
    }
    private void updatePro(Context context) {
        list=new ArrayList<>();
        String JsonData = getJson(context, "province.json");//获取assets目录下的json文件数据
        jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int m=0;m<jsonBean.size();m++)
        {
            list.add(jsonBean.get(m).getName());
        }

        setDataList(list);
    }
    public void setSelectedPro(String mSelectedPro, boolean smoothScroll) {
        setCurrentPosition(list.indexOf(mSelectedPro), smoothScroll);
    }
    public void setOnProSelectedListener(OnProSelectedListener onProSelectedListener) {
        this.mOnProSelectedListener = onProSelectedListener;
    }

    public String getSelectedPro() {
        return mSelectedPro;
    }

    public interface OnProSelectedListener {
        void onProSelected(String year, int position);
    }
    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return detail;
    }




}
