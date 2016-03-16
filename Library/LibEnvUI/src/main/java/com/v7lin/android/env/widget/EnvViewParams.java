package com.v7lin.android.env.widget;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
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
