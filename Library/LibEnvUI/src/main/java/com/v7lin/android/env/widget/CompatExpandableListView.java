package com.v7lin.android.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class CompatExpandableListView extends ExpandableListView implements XExpandableListViewCall, EnvCallback<ExpandableListView, XExpandableListViewCall> {

	private EnvUIChanger<ExpandableListView, XExpandableListViewCall> mEnvUIChanger;

	public CompatExpandableListView(Context context) {
		super(context);
	}

	public CompatExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CompatExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public CompatExpandableListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
	public void setChildDivider(Drawable childDivider) {
		super.setChildDivider(childDivider);

		applyAttr(android.R.attr.childDivider, 0);
	}

	@Override
	public void setChildIndicator(Drawable childIndicator) {
		super.setChildIndicator(childIndicator);

		applyAttr(android.R.attr.childIndicator, 0);
	}

	@Override
	public void setGroupIndicator(Drawable groupIndicator) {
		super.setGroupIndicator(groupIndicator);

		applyAttr(android.R.attr.groupIndicator, 0);
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
	public void scheduleGroupIndicator(Drawable groupIndicator) {
		super.setGroupIndicator(groupIndicator);
	}

	@Override
	public void scheduleChildIndicator(Drawable childIndicator) {
		super.setChildIndicator(childIndicator);
	}

	@Override
	public void scheduleChildDivider(Drawable childDivider) {
		super.setChildDivider(childDivider);
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
	public EnvUIChanger<ExpandableListView, XExpandableListViewCall> ensureEnvUIChanger(EnvResBridge bridge, boolean allowSysRes) {
		if (mEnvUIChanger == null) {
			mEnvUIChanger = new EnvExpandableListViewChanger<ExpandableListView, XExpandableListViewCall>(getContext(), bridge, allowSysRes);
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
