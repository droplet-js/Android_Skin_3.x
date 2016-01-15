package com.v7lin.android.support.app;

import com.v7lin.android.env.app.EnvSkinConfig;
import com.v7lin.android.support.v7.env.EnvViewMapV7;

public class SupportAppConfig extends EnvSkinConfig {
    @Override
    public void onCreate() {
        super.onCreate();
        EnvViewMapV7.init();
    }
}
