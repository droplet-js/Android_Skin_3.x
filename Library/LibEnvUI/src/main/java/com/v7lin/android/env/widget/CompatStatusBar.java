package com.v7lin.android.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class CompatStatusBar extends CompatLinearLayout {

	public CompatStatusBar(Context context) {
		super(context);
	}

	public CompatStatusBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public CompatStatusBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public CompatStatusBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}
}
