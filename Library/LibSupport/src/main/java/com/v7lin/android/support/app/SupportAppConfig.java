package com.v7lin.android.support.app;

import com.v7lin.android.env.app.EnvSkinAppConfig;
import com.v7lin.android.support.v7.env.EnvViewMapV7;

public class SupportAppConfig extends EnvSkinAppConfig {
    @Override
    public void onCreate() {
        super.onCreate();
        EnvViewMapV7.init();
    }
}
