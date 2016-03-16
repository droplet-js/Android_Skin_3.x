package com.v7lin.android.env.app;

import android.app.Application;

import com.v7lin.android.env.EnvResManager;
import com.v7lin.android.env.impl.SharedPrefSetup;
import com.v7lin.android.env.impl.VerSkinChecker;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public class EnvSkinAppConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EnvResManager.getGlobal().setEnvSetup(SharedPrefSetup.getGlobal());
        EnvResManager.getGlobal().setSkinChecker(VerSkinChecker.newInstance(1000, false));
    }
}
