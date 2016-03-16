package com.v7lin.android.env;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;

import com.v7lin.android.env.font.FontFamily;
import com.v7lin.android.env.impl.NullResMap;
import com.v7lin.android.env.skin.SkinFamily;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvResBridge {

    //
    private final Context mContext;
    private final Resources mOriginalRes;
    private final EnvResManager mEnvResManager;

    //
    private final String mPackageName;

    //
    private final AtomicBoolean mInitSkin = new AtomicBoolean(false);
    private String mSkinPath;
    private SkinFamily mSkinFamily;

    //
    private final AtomicBoolean mInitFont = new AtomicBoolean(false);
    private String mFontPath;
    private FontFamily mFontFamily;

    //
    private SystemResMap mSystemResMap = NullResMap.getInstance();

    public EnvResBridge(Context context, Resources original, EnvResManager manager) {
        super();
        mContext = context;
        mOriginalRes = original;
        mEnvResManager = manager;

        checkAsset();

        mPackageName = mContext.getPackageName();

        ensureSkinFamily();
        ensureFontFamily();
    }

    private void checkAsset() {
        if (mContext == null) {
            throw new IllegalArgumentException("Context is not valid!");
        }
        if (mOriginalRes == null) {
            throw new IllegalArgumentException("OriginalRes is not valid!");
        }
        if (mEnvResManager == null) {
            throw new IllegalArgumentException("EnvResManager is not valid!");
        }
    }

    public Resources getOriginalRes() {
        return mOriginalRes;
    }

    public void setSystemResMap(SystemResMap systemResMap) {
        mSystemResMap = systemResMap != null ? systemResMap : NullResMap.getInstance();
    }

    private synchronized void ensureSkinFamily() {
        if (mInitSkin.compareAndSet(false, true) || mEnvResManager.isSkinChanged(mContext, mSkinPath)) {
            mSkinPath = mEnvResManager.getSkinPath(mContext);
            mSkinFamily = mEnvResManager.getSkinFamily(mContext, this);
        }
    }

    private synchronized void ensureFontFamily() {
        if (mInitFont.compareAndSet(false, true) || mEnvResManager.isFontChanged(mContext, mFontPath)) {
            mFontPath = mEnvResManager.getFontPath(mContext);
            mFontFamily = mEnvResManager.getFontFamily(mContext);
        }
    }

    private EnvRes mappingSystemRes(int resid) {
        final String resourcePackageName = mOriginalRes.getResourcePackageName(resid);
        final String resourceTypeName = mOriginalRes.getResourceTypeName(resid);
        final String resourceEntryName = mOriginalRes.getResourceEntryName(resid);
        final int mappingid = mSystemResMap.mapping(mContext, resid, resourcePackageName, resourceTypeName, resourceEntryName);
        return new EnvRes(mappingid);
    }

    public CharSequence getText(int id) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getText(resid);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        return mOriginalRes.getText(resid);
    }

    public float getDimension(int id) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getDimension(resid);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        return mOriginalRes.getDimension(resid);
    }

    public int getDimensionPixelOffset(int id) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getDimensionPixelOffset(resid);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        return mOriginalRes.getDimensionPixelOffset(resid);
    }

    public int getDimensionPixelSize(int id) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getDimensionPixelSize(resid);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        return mOriginalRes.getDimensionPixelSize(resid);
    }

    public Drawable getDrawable(int id, Resources.Theme theme) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getDrawable(resid, theme);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return mOriginalRes.getDrawable(resid, theme);
        } else {
            return mOriginalRes.getDrawable(resid);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public Drawable getDrawableForDensity(int id, int density, Resources.Theme theme) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getDrawableForDensity(resid, density, theme);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return mOriginalRes.getDrawableForDensity(resid, density, theme);
        } else {
            return mOriginalRes.getDrawableForDensity(resid, density);
        }
    }

    public int getColor(int id, Resources.Theme theme) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getColor(resid, theme);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mOriginalRes.getColor(resid, theme);
        } else {
            return mOriginalRes.getColor(resid);
        }
    }

    public ColorStateList getColorStateList(int id, Resources.Theme theme) throws Resources.NotFoundException {
        ensureSkinFamily();
        final EnvRes mapping = mappingSystemRes(id);
        final int resid = mapping != null && mapping.isValid() ? mapping.getResid() : id;
        if (mSkinFamily != null) {
            String packageName = mOriginalRes.getResourcePackageName(resid);
            if (TextUtils.equals(packageName, mPackageName)) {
                // 换
                try {
                    return mSkinFamily.getColorStateList(resid, theme);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mOriginalRes.getColorStateList(resid, theme);
        } else {
            return mOriginalRes.getColorStateList(resid);
        }
    }

    public Typeface getTypeface() {
        ensureFontFamily();
        return mFontFamily != null ? mFontFamily.getTypeface() : null;
    }

    public SkinFamily getSkinFamily() {
        return mSkinFamily;
    }

    public FontFamily getFontFamily() {
        return mFontFamily;
    }
}
