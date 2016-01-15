package com.v7lin.android.support.v7.env.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvResBridge;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.widget.EnvCompoundButtonChanger;
import com.v7lin.android.support.R;

import java.util.Arrays;

public class EnvSwitchCompatChanger<SC extends SwitchCompat, SCC extends XSwitchCompatCall> extends EnvCompoundButtonChanger<SC, SCC> {

    private static final int[] ATTRS = {
            android.R.attr.thumb,
            R.attr.track
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mThumbEnvRes;
    private EnvRes mTrackEnvRes;

    public EnvSwitchCompatChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), getOriginalRes(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mThumbEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.thumb), allowSysRes);
        mTrackEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, R.attr.track), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(SC view, SCC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        if (attr == android.R.attr.thumb) {
            EnvRes res = new EnvRes(resid);
            mThumbEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//            scheduleThumbDrawable(view, call);
        } else if (attr == R.attr.track) {
            EnvRes res = new EnvRes(resid);
            mTrackEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//            scheduleTrackDrawable(view, call);
        }
    }

    @Override
    protected void onScheduleSkin(SC view, SCC call) {
        super.onScheduleSkin(view, call);
        scheduleThumbDrawable(view, call);
        scheduleTrackDrawable(view, call);
    }

    private void scheduleThumbDrawable(SC view, SCC call) {
        if (mThumbEnvRes != null) {
            Drawable drawable = getDrawable(mThumbEnvRes.getResid());
            if (drawable != null) {
                call.scheduleThumbDrawable(drawable);
            }
        }
    }

    private void scheduleTrackDrawable(SC view, SCC call) {
        if (mTrackEnvRes != null) {
            Drawable drawable = getDrawable(mTrackEnvRes.getResid());
            if (drawable != null) {
                call.scheduleTrackDrawable(drawable);
            }
        }
    }
}
