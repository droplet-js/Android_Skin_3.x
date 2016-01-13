package com.v7lin.android.support.v7.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.EnvResBridge;
import com.v7lin.android.env.widget.EnvUIChanger;
import com.v7lin.android.env.widget.EnvViewGroupChanger;
import com.v7lin.android.env.widget.XViewGroupCall;

@SuppressWarnings("deprecation")
public class CompatToolbar extends Toolbar implements XViewGroupCall, EnvCallback<ViewGroup, XViewGroupCall> {

    private EnvUIChanger<ViewGroup, XViewGroupCall> mEnvUIChanger;

    public CompatToolbar(Context context) {
        super(context);
    }

    public CompatToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompatToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    public EnvUIChanger<ViewGroup, XViewGroupCall> ensureEnvUIChanger(EnvResBridge bridge, boolean allowSysRes) {
        if (mEnvUIChanger == null) {
            mEnvUIChanger = new EnvViewGroupChanger<ViewGroup, XViewGroupCall>(getContext(), bridge, allowSysRes);
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
