package com.v7lin.android.support.v7.env.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.EnvResBridge;
import com.v7lin.android.env.widget.EnvUIChanger;
import com.v7lin.android.support.R;

@SuppressWarnings("deprecation")
public class CompatSwitchCompat extends SwitchCompat implements XSwitchCompatCall, EnvCallback<SwitchCompat, XSwitchCompatCall> {

    private EnvUIChanger<SwitchCompat, XSwitchCompatCall> mEnvUIChanger;

    public CompatSwitchCompat(Context context) {
        super(context);
    }

    public CompatSwitchCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompatSwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setThumbDrawable(Drawable thumb) {
        super.setThumbDrawable(thumb);

        applyAttrThumbDrawable(0);
    }

    @Override
    public void setThumbResource(int resId) {
        super.setThumbResource(resId);

        applyAttrThumbDrawable(resId);
    }

    private void applyAttrThumbDrawable(int resid) {
        applyAttr(android.R.attr.thumb, resid);
    }

    @Override
    public void setTrackDrawable(Drawable track) {
        super.setTrackDrawable(track);

        applyAttrTrackDrawable(0);
    }

    @Override
    public void setTrackResource(int resId) {
        super.setTrackResource(resId);

        applyAttrTrackDrawable(resId);
    }

    private void applyAttrTrackDrawable(int resid) {
        applyAttr(R.attr.track, resid);
    }

    @Override
    public void setButtonDrawable(int resid) {
        super.setButtonDrawable(resid);

        applyAttrButtonDrawable(resid);
    }

    @Override
    public void setButtonDrawable(Drawable d) {
        super.setButtonDrawable(d);

        applyAttrButtonDrawable(0);
    }

    private void applyAttrButtonDrawable(int resid) {
        applyAttr(android.R.attr.button, resid);
    }

    @Override
    public void setTextAppearance(Context context, int resid) {
        super.setTextAppearance(context, resid);

        applyAttr(android.R.attr.textAppearance, resid);
    }

    @Override
    public void setHighlightColor(int color) {
        super.setHighlightColor(color);

        applyAttr(android.R.attr.textColorHighlight, 0);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);

        applyAttrTextColor(0);
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);

        applyAttrTextColor(0);
    }

    private void applyAttrTextColor(int resid) {
        applyAttr(android.R.attr.textColor, resid);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawables(left, top, right, bottom);

        applyAttrCompoundDrawables(0, 0, 0, 0);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);

        applyAttrCompoundDrawables(0, 0, 0, 0);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);

        applyAttrCompoundDrawables(left, top, right, bottom);
    }

    private void applyAttrCompoundDrawables(int left, int top, int right, int bottom) {
        applyAttr(android.R.attr.drawableLeft, left);
        applyAttr(android.R.attr.drawableTop, top);
        applyAttr(android.R.attr.drawableRight, right);
        applyAttr(android.R.attr.drawableBottom, bottom);
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
    public void scheduleThumbDrawable(Drawable d) {
        super.setThumbDrawable(d);
    }

    @Override
    public void scheduleTrackDrawable(Drawable d) {
        super.setTrackDrawable(d);
    }

    @Override
    public void scheduleButtonDrawable(Drawable d) {
        super.setButtonDrawable(d);
    }

    @Override
    public void scheduleHighlightColor(int color) {
        super.setHighlightColor(color);
    }

    @Override
    public void scheduleTextColor(ColorStateList colors) {
        super.setTextColor(colors);
    }

    @Override
    public void scheduleHintTextColor(ColorStateList colors) {
        // super.setHintTextColor(colors);
        setHintTextColor(colors);
    }

    @Override
    public void scheduleLinkTextColor(ColorStateList colors) {
        // super.setLinkTextColor(colors);
        setLinkTextColor(colors);
    }

    @Override
    public void scheduleCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
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
    public EnvUIChanger<SwitchCompat, XSwitchCompatCall> ensureEnvUIChanger(EnvResBridge bridge, boolean allowSysRes) {
        if (mEnvUIChanger == null) {
            mEnvUIChanger = new EnvSwitchCompatChanger<SwitchCompat, XSwitchCompatCall>(getContext(), bridge, allowSysRes);
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
