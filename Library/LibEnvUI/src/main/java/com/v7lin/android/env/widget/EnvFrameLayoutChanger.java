package com.v7lin.android.env.widget;

import android.content.Context;
import android.widget.FrameLayout;

import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvFrameLayoutChanger<FL extends FrameLayout, FLC extends XFrameLayoutCall> extends EnvViewGroupChanger<FL, FLC> {

    public EnvFrameLayoutChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }
}
