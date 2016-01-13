package com.v7lin.android.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class CompatListView extends ListView implements XListViewCall, EnvCallback<ListView, XListViewCall> {

	private EnvUIChanger<ListView, XListViewCall> mEnvUIChanger;

	public CompatListView(Context context) {
		super(context);
	}

	public CompatListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CompatListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public CompatListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		if (child instanceof EnvCallback) {
			((EnvCallback) child).scheduleSkin();
		}
		return super.drawChild(canvas, child, drawingTime);
	}

	@Override
	public void setDivider(Drawable divider) {
		super.setDivider(divider);

		applyAttr(android.R.attr.divider, 0);
	}

	@Override
	public void setSelector(int resID) {
		super.setSelector(resID);

		applyAttrSelector(resID);
	}

	@Override
	public void setSelector(Drawable sel) {
		super.setSelector(sel);

		applyAttrSelector(0);
	}

	private void applyAttrSelector(int resid) {
		applyAttr(android.R.attr.listSelector, resid);
	}

	@Override
	public void setCacheColorHint(int color) {
		super.setCacheColorHint(color);

		applyAttr(android.R.attr.cacheColorHint, 0);
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
	public void scheduleDivider(Drawable divider) {
		super.setDivider(divider);
	}

	@Override
	public void scheduleSelector(Drawable sel) {
		super.setSelector(sel);
	}

	@Override
	public void scheduleCacheColorHint(int color) {
		super.setCacheColorHint(color);
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
	public EnvUIChanger<ListView, XListViewCall> ensureEnvUIChanger(EnvResBridge bridge, boolean allowSysRes) {
		if (mEnvUIChanger == null) {
			mEnvUIChanger = new EnvListViewChanger<ListView, XListViewCall>(getContext(), bridge, allowSysRes);
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
