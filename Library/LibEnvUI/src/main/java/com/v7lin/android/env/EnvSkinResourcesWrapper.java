package com.v7lin.android.env;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class EnvSkinResourcesWrapper extends EnvResourcesWrapper {

    private final EnvResBridge mEnvResBridge;

    public EnvSkinResourcesWrapper(Resources resources, EnvResBridge bridge) {
        super(resources);
        mEnvResBridge = bridge;
    }

    @Override
    public Drawable getDrawable(int id) throws NotFoundException {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getDrawable(id, null);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getDrawable(id);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Drawable getDrawable(int id, Theme theme) throws NotFoundException {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getDrawable(id, theme);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getDrawable(id, theme);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public Drawable getDrawableForDensity(int id, int density) throws NotFoundException {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getDrawableForDensity(id, density, null);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getDrawableForDensity(id, density);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Drawable getDrawableForDensity(int id, int density, Theme theme) {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getDrawableForDensity(id, density, theme);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getDrawableForDensity(id, density, theme);
    }

    @Override
    public int getColor(int id) throws NotFoundException {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getColor(id, null);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getColor(id);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public int getColor(int id, Theme theme) throws NotFoundException {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getColor(id, theme);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getColor(id, theme);
    }

    @Override
    public ColorStateList getColorStateList(int id) throws NotFoundException {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getColorStateList(id, null);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getColorStateList(id);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public ColorStateList getColorStateList(int id, Theme theme) throws NotFoundException {
        try {
            if (mEnvResBridge != null) {
                return mEnvResBridge.getColorStateList(id, theme);
            }
        } catch (Resources.NotFoundException e) {
        }
        return super.getColorStateList(id, theme);
    }
}
