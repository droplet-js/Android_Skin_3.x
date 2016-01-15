package com.v7lin.android.env.widget;

import java.util.Arrays;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvCompoundButtonChanger<CB extends CompoundButton, CBC extends XCompoundButtonCall> extends EnvTextViewChanger<CB, CBC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.button
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mButtonEnvRes;

    public EnvCompoundButtonChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), getOriginalRes(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mButtonEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.button), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(CB view, CBC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.button: {
                EnvRes res = new EnvRes(resid);
                mButtonEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//                scheduleButtonDrawable(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(CB view, CBC call) {
        super.onScheduleSkin(view, call);
        scheduleButtonDrawable(view, call);
    }

    private void scheduleButtonDrawable(CB view, CBC call) {
        if (mButtonEnvRes != null) {
            Drawable drawable = getDrawable(mButtonEnvRes.getResid());
            if (drawable != null) {
                call.scheduleButtonDrawable(drawable);
            }
        }
    }
}
