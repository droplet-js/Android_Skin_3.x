package com.v7lin.android.env.widget;

import java.util.Arrays;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ListView;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvListViewChanger<LV extends ListView, LVC extends XListViewCall> extends EnvAbsListViewChanger<LV, LVC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.divider
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mDividerEnvRes;

    public EnvListViewChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), getOriginalRes(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mDividerEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.divider), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(LV view, LVC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.divider: {
                EnvRes res = new EnvRes(resid);
                mDividerEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//                scheduleDivider(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(LV view, LVC call) {
        super.onScheduleSkin(view, call);
        scheduleDivider(view, call);
    }

    private void scheduleDivider(LV view, LVC call) {
        if (mDividerEnvRes != null) {
            Drawable drawable = getDrawable(mDividerEnvRes.getResid());
            if (drawable != null) {
                call.scheduleDivider(drawable);
            }
        }
    }
}
