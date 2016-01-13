package com.v7lin.android.support.app;

import com.v7lin.android.env.app.EnvAppConfig;
import com.v7lin.android.support.v7.env.EnvViewMapV7;

public class SupportAppConfig extends EnvAppConfig {
    @Override
    public void onCreate() {
        super.onCreate();
        EnvViewMapV7.init();
    }
}
