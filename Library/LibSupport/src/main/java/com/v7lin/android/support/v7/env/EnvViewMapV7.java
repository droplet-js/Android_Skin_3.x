package com.v7lin.android.support.v7.env;

import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.v7lin.android.env.widget.EnvViewMap;
import com.v7lin.android.env.widget.EnvViewParams;
import com.v7lin.android.support.R;
import com.v7lin.android.support.v7.env.widget.CompatSwitchCompat;
import com.v7lin.android.support.v7.env.widget.CompatToolbar;

public class EnvViewMapV7 {

    static {
        EnvViewMap.getInstance().assetParams(Toolbar.class, new EnvViewParams(R.attr.toolbarStyle, 0, false));
        EnvViewMap.getInstance().assetParams(SearchView.class, new EnvViewParams(R.attr.searchViewStyle, 0, false));
        EnvViewMap.getInstance().assetParams(SwitchCompat.class, new EnvViewParams(R.attr.switchStyle, 0, false));

        EnvViewMap.getInstance().assetThirdTranser(Toolbar.class, CompatToolbar.class);
        EnvViewMap.getInstance().assetThirdTranser(SwitchCompat.class, CompatSwitchCompat.class);
    }

    public static void init() {

    }
}
