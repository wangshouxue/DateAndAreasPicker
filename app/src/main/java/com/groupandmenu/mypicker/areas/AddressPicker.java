package com.groupandmenu.mypicker.areas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.groupandmenu.mypicker.R;


public class AddressPicker extends LinearLayout implements ProPicker.OnProSelectedListener,CityPicker.OnCitySelectedListener{
    private ProPicker mProPicker;
    private CityPicker mCityPicker;
    private OnAddressSelectedListener mOnAddressSelectedListener;

    @Override
    public void onCitySelected(String city) {
        onDateSelected();
    }

    @Override
    public void onProSelected(String pro, int position) {
        mCityPicker.setPro(pro,position);

        onDateSelected();
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param context the context
     */
    public AddressPicker(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public AddressPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public AddressPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_city, this);
        initChild();
        initAttrs(context, attrs);
        mProPicker.setBackgroundDrawable(getBackground());
        mCityPicker.setBackgroundDrawable(getBackground());
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePicker);
        int textSize = a.getDimensionPixelSize(R.styleable.DatePicker_itemTextSize,
                getResources().getDimensionPixelSize(R.dimen.WheelItemTextSize));
        int textColor = a.getColor(R.styleable.DatePicker_itemTextColor,
                Color.BLACK);
        boolean isTextGradual = a.getBoolean(R.styleable.DatePicker_textGradual, true);
        boolean isCyclic = a.getBoolean(R.styleable.DatePicker_wheelCyclic, false);
        int halfVisibleItemCount = a.getInteger(R.styleable.DatePicker_halfVisibleItemCount, 2);
        int selectedItemTextColor = a.getColor(R.styleable.DatePicker_selectedTextColor,
                getResources().getColor(R.color.com_ycuwq_datepicker_selectedTextColor));
        int selectedItemTextSize = a.getDimensionPixelSize(R.styleable.DatePicker_selectedTextSize,
                getResources().getDimensionPixelSize(R.dimen.WheelSelectedItemTextSize));
        int itemWidthSpace = a.getDimensionPixelSize(R.styleable.DatePicker_itemWidthSpace,
                getResources().getDimensionPixelOffset(R.dimen.WheelItemWidthSpace));
        int itemHeightSpace = a.getDimensionPixelSize(R.styleable.DatePicker_itemHeightSpace,
                getResources().getDimensionPixelOffset(R.dimen.WheelItemHeightSpace));
        boolean isZoomInSelectedItem = a.getBoolean(R.styleable.DatePicker_zoomInSelectedItem, true);
        boolean isShowCurtain = a.getBoolean(R.styleable.DatePicker_wheelCurtain, true);
        int curtainColor = a.getColor(R.styleable.DatePicker_wheelCurtainColor, Color.WHITE);
        boolean isShowCurtainBorder = a.getBoolean(R.styleable.DatePicker_wheelCurtainBorder, true);
        int curtainBorderColor = a.getColor(R.styleable.DatePicker_wheelCurtainBorderColor,
                getResources().getColor(R.color.com_ycuwq_datepicker_divider));
        a.recycle();

        setTextSize(textSize);
        setTextColor(textColor);
        setTextGradual(isTextGradual);
        setCyclic(isCyclic);
        setHalfVisibleItemCount(halfVisibleItemCount);
        setSelectedItemTextColor(selectedItemTextColor);
        setSelectedItemTextSize(selectedItemTextSize);
        setItemWidthSpace(itemWidthSpace);
        setItemHeightSpace(itemHeightSpace);
        setZoomInSelectedItem(isZoomInSelectedItem);
        setShowCurtain(isShowCurtain);
        setCurtainColor(curtainColor);
        setShowCurtainBorder(isShowCurtainBorder);
        setCurtainBorderColor(curtainBorderColor);
    }
    private void initChild() {
        mProPicker = findViewById(R.id.pro_layout_address);
        mProPicker.setOnProSelectedListener(this);
        mCityPicker = findViewById(R.id.city_layout_address);
        mCityPicker.setOnCitySelectedListener(this);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        if (mProPicker != null && mCityPicker != null ) {
            mProPicker.setBackgroundColor(color);
            mCityPicker.setBackgroundColor(color);
        }
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
        if (mProPicker != null && mCityPicker != null) {
            mProPicker.setBackgroundResource(resid);
            mCityPicker.setBackgroundResource(resid);
        }
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        if (mProPicker != null && mCityPicker != null) {
            mProPicker.setBackgroundDrawable(background);
            mCityPicker.setBackgroundDrawable(background);
        }
    }

    private void onDateSelected() {
        if (mOnAddressSelectedListener != null) {
            mOnAddressSelectedListener.onAddressSelected(getPro(),
                    getCity());
        }
    }

    /**
     * Sets date.
     */
    public void setAddress(String pro,String city) {
        setAddress(pro, city,true);
    }


    public void setAddress(String pro,String city, boolean smoothScroll) {
        mProPicker.setSelectedPro(pro, smoothScroll);
        mCityPicker.setSelectedCity(city, smoothScroll);
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public String getPro() {
        return mProPicker.getSelectedPro();
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public String getCity() {
        return mCityPicker.getSelectedCity();
    }

    /**
     * Gets year picker.
     *
     * @return the year picker
     */
    public ProPicker getProPicker() {
        return mProPicker;
    }

    /**
     * Gets month picker.
     *
     * @return the month picker
     */
    public CityPicker getCityPicker() {
        return mCityPicker;
    }


    /**
     * 一般列表的文本颜色
     *
     * @param textColor 文本颜色
     */
    public void setTextColor(@ColorInt int textColor) {
        mCityPicker.setTextColor(textColor);
        mProPicker.setTextColor(textColor);
    }

    /**
     * 一般列表的文本大小
     *
     * @param textSize 文字大小
     */
    public void setTextSize(int textSize) {
        mCityPicker.setTextSize(textSize);
        mProPicker.setTextSize(textSize);
    }

    /**
     * 设置被选中时候的文本颜色
     *
     * @param selectedItemTextColor 文本颜色
     */
    public void setSelectedItemTextColor(@ColorInt int selectedItemTextColor) {
        mCityPicker.setSelectedItemTextColor(selectedItemTextColor);
        mProPicker.setSelectedItemTextColor(selectedItemTextColor);
    }

    /**
     * 设置被选中时候的文本大小
     *
     * @param selectedItemTextSize 文字大小
     */
    public void setSelectedItemTextSize(int selectedItemTextSize) {
        mCityPicker.setSelectedItemTextSize(selectedItemTextSize);
        mProPicker.setSelectedItemTextSize(selectedItemTextSize);
    }


    /**
     * 设置显示数据量的个数的一半。
     * 为保证总显示个数为奇数,这里将总数拆分，itemCount = mHalfVisibleItemCount * 2 + 1
     *
     * @param halfVisibleItemCount 总数量的一半
     */
    public void setHalfVisibleItemCount(int halfVisibleItemCount) {
        mCityPicker.setHalfVisibleItemCount(halfVisibleItemCount);
        mProPicker.setHalfVisibleItemCount(halfVisibleItemCount);
    }

    /**
     * Sets item width space.
     *
     * @param itemWidthSpace the item width space
     */
    public void setItemWidthSpace(int itemWidthSpace) {
        mCityPicker.setItemWidthSpace(itemWidthSpace);
        mProPicker.setItemWidthSpace(itemWidthSpace);
    }

    /**
     * 设置两个Item之间的间隔
     *
     * @param itemHeightSpace 间隔值
     */
    public void setItemHeightSpace(int itemHeightSpace) {
        mCityPicker.setItemHeightSpace(itemHeightSpace);
        mProPicker.setItemHeightSpace(itemHeightSpace);
    }


    /**
     * Set zoom in center item.
     *
     * @param zoomInSelectedItem the zoom in center item
     */
    public void setZoomInSelectedItem(boolean zoomInSelectedItem) {
        mCityPicker.setZoomInSelectedItem(zoomInSelectedItem);
        mProPicker.setZoomInSelectedItem(zoomInSelectedItem);
    }

    /**
     * 设置是否循环滚动。
     * set wheel cyclic
     * @param cyclic 上下边界是否相邻
     */
    public void setCyclic(boolean cyclic) {
        mCityPicker.setCyclic(cyclic);
        mProPicker.setCyclic(cyclic);
    }

    /**
     * 设置文字渐变，离中心越远越淡。
     * Set the text color gradient
     * @param textGradual 是否渐变
     */
    public void setTextGradual(boolean textGradual) {
        mCityPicker.setTextGradual(textGradual);
        mProPicker.setTextGradual(textGradual);
    }


    /**
     * 设置中心Item是否有幕布遮盖
     * set the center item curtain cover
     * @param showCurtain 是否有幕布
     */
    public void setShowCurtain(boolean showCurtain) {
        mCityPicker.setShowCurtain(showCurtain);
        mProPicker.setShowCurtain(showCurtain);
    }

    /**
     * 设置幕布颜色
     * set curtain color
     * @param curtainColor 幕布颜色
     */
    public void setCurtainColor(@ColorInt int curtainColor) {
        mCityPicker.setCurtainColor(curtainColor);
        mProPicker.setCurtainColor(curtainColor);
    }

    /**
     * 设置幕布是否显示边框
     * set curtain border
     * @param showCurtainBorder 是否有幕布边框
     */
    public void setShowCurtainBorder(boolean showCurtainBorder) {
        mCityPicker.setShowCurtainBorder(showCurtainBorder);
        mProPicker.setShowCurtainBorder(showCurtainBorder);
    }

    /**
     * 幕布边框的颜色
     * curtain border color
     * @param curtainBorderColor 幕布边框颜色
     */
    public void setCurtainBorderColor(@ColorInt int curtainBorderColor) {
        mCityPicker.setCurtainBorderColor(curtainBorderColor);
        mProPicker.setCurtainBorderColor(curtainBorderColor);
    }

    /**
     * 设置选择器的指示器文本
     * set indicator text
     * @param yearText  年指示器文本
     * @param monthText 月指示器文本
     * @param dayText   日指示器文本
     */
    public void setIndicatorText(String yearText, String monthText, String dayText) {
        mProPicker.setIndicatorText(yearText);
        mCityPicker.setIndicatorText(monthText);
    }

    /**
     * 设置指示器文字的颜色
     * set indicator text color
     * @param textColor 文本颜色
     */
    public void setIndicatorTextColor(@ColorInt int textColor) {
        mProPicker.setIndicatorTextColor(textColor);
        mCityPicker.setIndicatorTextColor(textColor);
    }

    /**
     * 设置指示器文字的大小
     *  indicator text size
     * @param textSize 文本大小
     */
    public void setIndicatorTextSize(int textSize) {
        mProPicker.setTextSize(textSize);
        mCityPicker.setTextSize(textSize);
    }

    /**
     * Sets on date selected listener.
     *
     */
    public void setOnAddressSelectedListener(OnAddressSelectedListener onAddressSelectedListener) {
        this.mOnAddressSelectedListener = onAddressSelectedListener;
    }

    /**
     * The interface On date selected listener.
     */
    public interface OnAddressSelectedListener {
        void onAddressSelected(String pro, String city);
    }
}
