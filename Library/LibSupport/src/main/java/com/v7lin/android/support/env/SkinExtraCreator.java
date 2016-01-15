package com.v7lin.android.support.env;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvResBridge;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.extra.EnvExtraCreator;
import com.v7lin.android.env.skin.SkinFamily;
import com.v7lin.android.support.R;

import java.util.Arrays;

@SuppressWarnings("deprecation")
public class SkinExtraCreator implements EnvExtraCreator<SkinData, SkinFamily> {

    private static final int[] ATTRS = { R.attr.colorPrimary };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvResBridge mEnvResBridge;

    public SkinExtraCreator(EnvResBridge bridge) {
        super();
        mEnvResBridge = bridge;
    }

    @Override
    public SkinData createFrom(Context context, SkinFamily data, SkinFamily compare) {
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(context, mEnvResBridge.getOriginalRes(), ATTRS);
        EnvRes res = array.getEnvRes(Arrays.binarySearch(ATTRS, R.attr.colorPrimary), false);
        array.recycle();

        final int skinNameResid = R.string.skin_name;
        CharSequence skinName = data.hasValue(skinNameResid) ? data.getText(skinNameResid) : context.getResources().getText(skinNameResid);
        final int skinBGResid = res != null ? res.getResid() : R.color.primary;
        Drawable skinBG = data.hasValue(skinBGResid) ? data.getDrawable(skinBGResid, null) : context.getResources().getDrawable(skinBGResid);
        return new SkinData(skinName, skinBG, data.getSkinPath());
    }
}
