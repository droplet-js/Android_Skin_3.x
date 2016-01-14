package com.v7lin.android.env.widget;

import java.util.Arrays;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.AbsListView;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvAbsListViewChanger<ALV extends AbsListView, ALVC extends XAbsListViewCall> extends EnvViewGroupChanger<ALV, ALVC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.listSelector,
            //
            android.R.attr.cacheColorHint
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mListSelectorEnvRes;
    private EnvRes mCacheColorHint;

    public EnvAbsListViewChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mListSelectorEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.listSelector), allowSysRes);
        mCacheColorHint = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.cacheColorHint), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(ALV view, ALVC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.listSelector: {
                EnvRes res = new EnvRes(resid);
                mListSelectorEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleSelector(view, call);
                break;
            }
            case android.R.attr.cacheColorHint: {
                EnvRes res = new EnvRes(resid);
                mCacheColorHint = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleCacheColorHint(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(ALV view, ALVC call) {
        super.onScheduleSkin(view, call);
        scheduleSelector(view, call);
        scheduleCacheColorHint(view, call);
    }

    private void scheduleSelector(ALV view, ALVC call) {
        if (mListSelectorEnvRes != null) {
            Drawable drawable = getDrawable(mListSelectorEnvRes.getResid());
            if (drawable != null) {
                call.scheduleSelector(drawable);
            }
        }
    }

    private void scheduleCacheColorHint(ALV view, ALVC call) {
        if (mCacheColorHint != null) {
            int color = getColor(mCacheColorHint.getResid());
            call.scheduleCacheColorHint(color);
        }
    }
}
