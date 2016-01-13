package com.v7lin.android.env.widget;
public class EnvViewParams {
    public int defStyleAttr;
    public int defStyleRes;
    public boolean allowSysRes;

    public EnvViewParams(int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        this.defStyleAttr = defStyleAttr;
        this.defStyleRes = defStyleRes;
        this.allowSysRes = allowSysRes;
    }
}
