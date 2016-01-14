package com.v7lin.android.env.widget;

import java.util.Arrays;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.AbsSeekBar;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvAbsSeekBarChanger<ASB extends AbsSeekBar, ASBC extends XAbsSeekBarCall> extends EnvProgressBarChanger<ASB, ASBC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.thumb
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mThumbEnvRes;

    public EnvAbsSeekBarChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mThumbEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.thumb), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(ASB view, ASBC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.thumb: {
                EnvRes res = new EnvRes(resid);
                mThumbEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleThumb(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(ASB view, ASBC call) {
        super.onScheduleSkin(view, call);
        scheduleThumb(view, call);
    }

    private void scheduleThumb(ASB view, ASBC call) {
        if (mThumbEnvRes != null) {
            Drawable drawable = getDrawable(mThumbEnvRes.getResid());
            if (drawable != null) {
                call.scheduleThumb(drawable);
            }
        }
    }
}
