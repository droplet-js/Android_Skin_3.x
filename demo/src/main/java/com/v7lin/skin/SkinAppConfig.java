package com.v7lin.skin;

import com.v7lin.android.env.EnvResManager;
import com.v7lin.android.env.impl.SharedPrefSetup;
import com.v7lin.android.env.impl.VerSkinChecker;
import com.v7lin.android.os.env.PathUtils;
import com.v7lin.android.support.app.SupportAppConfig;

public class SkinAppConfig extends SupportAppConfig {

    @Override
    public void onCreate() {
        super.onCreate();

        PathUtils.setAppRtlPath(BuildConfig.PATH_ROOT);

        EnvResManager.getGlobal().setEnvSetup(SharedPrefSetup.getGlobal());
        EnvResManager.getGlobal().setSkinChecker(VerSkinChecker.newInstance(BuildConfig.SKIN_VER, BuildConfig.SKIN_CHECK_PKG));
    }
}