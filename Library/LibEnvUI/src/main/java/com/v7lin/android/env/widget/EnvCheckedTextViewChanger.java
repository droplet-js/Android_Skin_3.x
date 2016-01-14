package com.v7lin.android.env.widget;

import java.util.Arrays;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvCheckedTextViewChanger<CTV extends CheckedTextView, CTVC extends XCheckedTextViewCall> extends EnvTextViewChanger<CTV, CTVC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.checkMark
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mCheckMarkEnvRes;

    public EnvCheckedTextViewChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mCheckMarkEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.checkMark), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(CTV view, CTVC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.checkMark: {
                EnvRes res = new EnvRes(resid);
                mCheckMarkEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleCheckMarkDrawable(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(CTV view, CTVC call) {
        super.onScheduleSkin(view, call);
        scheduleCheckMarkDrawable(view, call);
    }

    private void scheduleCheckMarkDrawable(CTV view, CTVC call) {
        if (mCheckMarkEnvRes != null) {
            Drawable drawable = getDrawable(mCheckMarkEnvRes.getResid());
            if (drawable != null) {
                call.scheduleCheckMarkDrawable(drawable);
            }
        }
    }
}
