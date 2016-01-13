package com.v7lin.android.env.widget;

import java.util.Arrays;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvActivityChanger<ACT extends Activity, ACTC extends XActivityCall> extends EnvUIChanger<ACT, ACTC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.windowBackground
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mWindowBackgroundEnvRes;

    public EnvActivityChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), ATTRS);
        mWindowBackgroundEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.windowBackground), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(ACT activity, ACTC call, int attr, int resid, boolean allowSysRes) {
        switch (attr) {
            case android.R.attr.windowBackground: {
                EnvRes res = new EnvRes(resid);
                mWindowBackgroundEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

                scheduleWindowBackground(activity, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(ACT activity, ACTC call) {
        scheduleWindowBackground(activity, call);
        scheduleViewSkin(activity.findViewById(android.R.id.content));
    }

    private void scheduleWindowBackground(ACT activity, ACTC call) {
        if (mWindowBackgroundEnvRes != null) {
            Drawable drawable = getDrawable(mWindowBackgroundEnvRes.getResid());
            if (drawable != null) {
                call.scheduleBackgroundDrawable(drawable);
            }
        }
    }

    @Override
    protected void onScheduleFont(ACT activity, ACTC call) {
        scheduleViewFont(activity.findViewById(android.R.id.content));
    }
}
