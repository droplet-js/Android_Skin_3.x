package com.v7lin.android.support.v7.env.widget;

import android.graphics.drawable.Drawable;

import com.v7lin.android.env.widget.XCompoundButtonCall;

public interface XSwitchCompatCall extends XCompoundButtonCall {
    public void scheduleThumbDrawable(Drawable d);

    public void scheduleTrackDrawable(Drawable d);
}
