package com.groupandmenu.mypicker.areas;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.groupandmenu.mypicker.R;


/**
 * 时间选择器，弹出框
 * Created by ycuwq on 2018/1/6.
 */
public class DAddressDialogFragment extends DialogFragment {

	protected DetailAddressPicker mAddressPicker;
	private String mSelectedPro, mSelectedCity;
	private OnAddressChooseListener mOnAddressChooseListener;
	private boolean mIsShowAnimation = true;
	protected Button mCancelButton, mDecideButton;

	public void setOnAddressChooseListener(OnAddressChooseListener onDateChooseListener) {
		mOnAddressChooseListener= onDateChooseListener;
	}

	public void showAnimation(boolean show) {
		mIsShowAnimation = show;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_daddress, container);

		mAddressPicker = view.findViewById(R.id.picker_dialog);
		mCancelButton = view.findViewById(R.id.btn_dialog_address_cancel);
		mDecideButton = view.findViewById(R.id.btn_dialog_address_decide);
		mCancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		mDecideButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnAddressChooseListener != null) {
					mOnAddressChooseListener.onAddressChoose(mAddressPicker.getPro(),
							mAddressPicker.getCity(),mAddressPicker.getArea());
				}
				dismiss();
			}
		});

//		if (mSelectedYear > 0) {
			setSelectedDate();
//		}

		initChild();
		return view;
	}

	protected void initChild() {

	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new Dialog(getActivity(), R.style.DatePickerBottomDialog);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

		dialog.setContentView(R.layout.dialog_date);
		dialog.setCanceledOnTouchOutside(false); // 外部点击取消

		Window window = dialog.getWindow();
		if (window != null) {
			if (mIsShowAnimation) {
				window.getAttributes().windowAnimations = R.style.DatePickerDialogAnim;
			}
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.gravity = Gravity.BOTTOM; // 紧贴底部
			lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
			lp.dimAmount = 0.35f;
			window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		}

		return dialog;
	}

	public void setSelectedDate(String mSelectedPro, String mSelectedCity) {
		this.mSelectedPro = mSelectedPro;
		this.mSelectedCity = mSelectedCity;
		setSelectedDate();
	}

	private void setSelectedDate() {
		if (mAddressPicker != null) {
			mAddressPicker.setAddress(mSelectedPro, mSelectedCity,false);
		}
	}

	public interface OnAddressChooseListener {
		void onAddressChoose(String pro, String city, String area);
	}


}
