package com.v7lin.android.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class CompatView extends View implements XViewCall, EnvCallback<View, XViewCall> {

    private EnvUIChanger<View, XViewCall> mEnvUIChanger;

    public CompatView(Context context) {
        super(context);
    }

    public CompatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CompatView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mEnvUIChanger != null) {
            mEnvUIChanger.scheduleSkin(this, this, isInEditMode());
            mEnvUIChanger.scheduleFont(this, this, isInEditMode());
        }
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
    public EnvUIChanger<View, XViewCall> ensureEnvUIChanger(EnvResBridge bridge, boolean allowSysRes) {
        if (mEnvUIChanger == null) {
            mEnvUIChanger = new EnvViewChanger<View, XViewCall>(getContext(), bridge, allowSysRes);
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
}
