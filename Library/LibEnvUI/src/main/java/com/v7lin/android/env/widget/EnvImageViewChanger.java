package com.v7lin.android.env.widget;

import java.util.Arrays;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvImageViewChanger<IV extends ImageView, IVC extends XImageViewCall> extends EnvViewChanger<IV, IVC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.src
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mSrcEnvRes;

    public EnvImageViewChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mSrcEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.src), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(IV view, IVC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.src: {
                EnvRes res = new EnvRes(resid);
                mSrcEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleSrc(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(IV view, IVC call) {
        super.onScheduleSkin(view, call);
        scheduleSrc(view, call);
    }

    /**
     * @see ImageView#setImageResource(int)
     */
    private void scheduleSrc(IV view, IVC call) {
        if (mSrcEnvRes != null) {
            Drawable drawable = getDrawable(mSrcEnvRes.getResid());
            if (drawable != null) {
                call.scheduleImageDrawable(drawable);
            }
        }
    }
}
