package com.v7lin.android.support.env;

import android.graphics.drawable.Drawable;

public class SkinData {
    private CharSequence mSkinName;
    private Drawable mSkinBG;
    private String mSkinPath;

    public SkinData(CharSequence skinName, Drawable skinBG, String skinPath) {
        mSkinName = skinName;
        mSkinBG = skinBG;
        mSkinPath = skinPath;
    }

    public CharSequence getSkinName() {
        return mSkinName;
    }

    public Drawable getSkinBG() {
        return mSkinBG;
    }

    public String getSkinPath() {
        return mSkinPath;
    }
}
