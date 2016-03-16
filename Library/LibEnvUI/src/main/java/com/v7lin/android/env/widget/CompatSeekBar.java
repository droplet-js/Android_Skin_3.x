package com.v7lin.android.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.AbsSeekBar;
import android.widget.SeekBar;

import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.EnvResBridge;

/**
 * ProgressDrawable 中使用 ScaleDrawable，在小米 1 上显示不正常
 * 
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class CompatSeekBar extends SeekBar implements XAbsSeekBarCall, EnvCallback<AbsSeekBar, XAbsSeekBarCall> {

	private EnvUIChanger<AbsSeekBar, XAbsSeekBarCall> mEnvUIChanger;

	public CompatSeekBar(Context context) {
		super(context);
	}

	public CompatSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CompatSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public CompatSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public void setThumb(Drawable thumb) {
		super.setThumb(thumb);

		applyAttr(android.R.attr.thumb, 0);
	}

	@Override
	public void setIndeterminateDrawable(Drawable d) {
		super.setIndeterminateDrawable(d);

		applyAttr(android.R.attr.indeterminateDrawable, 0);
	}

	@Override
	public void setProgressDrawable(Drawable d) {
		super.setProgressDrawable(d);

		applyAttr(android.R.attr.progressDrawable, 0);
	}

	@Override
	public void setBackgroundColor(int color) {
		super.setBackgroundColor(color);

		applyAttrBackground(0);
	}

	@Override
	public void setBackgroundResource(int resid) {
		super.setBackgroundResource(resid);

		applyAttrBackground(resid);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void setBackground(Drawable background) {
		super.setBackground(background);

		applyAttrBackground(0);
	}

	@Override
	public void setBackgroundDrawable(Drawable background) {
		super.setBackgroundDrawable(background);

		applyAttrBackground(0);
	}

	private void applyAttrBackground(int resid) {
		applyAttr(android.R.attr.background, resid);
	}

	private void applyAttr(int attr, int resid) {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.applyAttr(this, this, attr, resid, isInEditMode());
		}
	}

	@Override
	public void scheduleThumb(Drawable thumb) {
		super.setThumb(thumb);
	}

	@Override
	public void scheduleIndeterminateDrawable(Drawable d) {
		super.setIndeterminateDrawable(d);
	}

	@Override
	public void scheduleProgressDrawable(Drawable d) {
		super.setProgressDrawable(d);
	}

	@Override
	public void scheduleForeground(Drawable foreground) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			super.setForeground(foreground);
		}
	}

	@Override
	public void scheduleBackgroundDrawable(Drawable background) {
		super.setBackgroundDrawable(background);
	}

	@Override
	public EnvUIChanger<AbsSeekBar, XAbsSeekBarCall> ensureEnvUIChanger(EnvResBridge bridge, boolean allowSysRes) {
		if (mEnvUIChanger == null) {
			mEnvUIChanger = new EnvAbsSeekBarChanger<AbsSeekBar, XAbsSeekBarCall>(getContext(), bridge, allowSysRes);
		}
		return mEnvUIChanger;
	}

	@Override
	public void scheduleSkin() {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleSkin(this, this, isInEditMode());
		}
	}

	@Override
	public void scheduleFont() {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleFont(this, this, isInEditMode());
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleSkin(this, this, isInEditMode());
			mEnvUIChanger.scheduleFont(this, this, isInEditMode());
		}
	}
}
