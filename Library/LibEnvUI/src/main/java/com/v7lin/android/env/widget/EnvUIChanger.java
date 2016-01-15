package com.v7lin.android.env.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.EnvResBridge;
import com.v7lin.android.env.EnvResManager;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public abstract class EnvUIChanger<UI, UIC> {

    private final Context mContext;
    private final EnvResBridge mEnvResBridge;
    private final boolean mAllowSysRes;

    private String mSkinPath = null;
    private String mFontPath = null;

    private final AtomicBoolean mInitSkinFlag = new AtomicBoolean(false);
    private final AtomicBoolean mInitFontFlag = new AtomicBoolean(false);

    public EnvUIChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super();
        mContext = context;
        mEnvResBridge = bridge;
        mAllowSysRes = allowSysRes;
    }

    protected final Context getContext() {
        return mContext;
    }

    protected final Resources getOriginalRes() {
        return mEnvResBridge.getOriginalRes();
    }

    protected final CharSequence getText(int id) throws Resources.NotFoundException {
        return mEnvResBridge.getText(id);
    }

    protected final float getDimension(int id) throws Resources.NotFoundException {
        return mEnvResBridge.getDimension(id);
    }

    protected final int getDimensionPixelOffset(int id) throws Resources.NotFoundException {
        return mEnvResBridge.getDimensionPixelOffset(id);
    }

    protected final int getDimensionPixelSize(int id) throws Resources.NotFoundException {
        return mEnvResBridge.getDimensionPixelSize(id);
    }

    protected final Drawable getDrawable(int id) throws Resources.NotFoundException {
        return mEnvResBridge.getDrawable(id, mContext.getTheme());
    }

    protected final int getColor(int id) throws Resources.NotFoundException {
        return mEnvResBridge.getColor(id, mContext.getTheme());
    }

    protected final ColorStateList getColorStateList(int id) throws Resources.NotFoundException {
        return mEnvResBridge.getColorStateList(id, mContext.getTheme());
    }

    protected final Typeface getTypeface() {
        return mEnvResBridge.getTypeface();
    }

    public final boolean isSkinChanged() {
        return EnvResManager.getGlobal().isSkinChanged(mContext, mSkinPath);
    }

    public final boolean isFontChanged() {
        return EnvResManager.getGlobal().isFontChanged(mContext, mFontPath);
    }

    public final void applyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean isInEditMode) {
        if (!isInEditMode) {
            onApplyStyle(attrs, defStyleAttr, defStyleRes, mAllowSysRes);
        }
    }

    protected abstract void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes);

    public final void applyAttr(UI ui, UIC call, int attr, int resid, boolean isInEditMode) {
        if (!isInEditMode) {
            onApplyAttr(ui, call, attr, resid, mAllowSysRes);
        }
    }

    protected abstract void onApplyAttr(UI ui, UIC call, int attr, int resid, boolean allowSysRes);

    public final void scheduleSkin(UI ui, UIC call, boolean isInEditMode) {
        if (!isInEditMode && (mInitSkinFlag.compareAndSet(false, true) || isSkinChanged())) {
            mSkinPath = EnvResManager.getGlobal().getSkinPath(mContext);
            onScheduleSkin(ui, call);
        }
    }

    protected abstract void onScheduleSkin(UI ui, UIC call);

    public final void scheduleFont(UI ui, UIC call, boolean isInEditMode) {
        if (!isInEditMode && (mInitFontFlag.compareAndSet(false, true) || isFontChanged())) {
            mFontPath = EnvResManager.getGlobal().getFontPath(mContext);
            onScheduleFont(ui, call);
        }
    }

    protected abstract void onScheduleFont(UI ui, UIC call);

    protected final void scheduleViewSkin(View view) {
        if (view != null) {
            if (view instanceof EnvCallback) {
                ((EnvCallback) view).scheduleSkin();
            } else if (view instanceof ViewGroup) {
                scheduleViewGroupSkin((ViewGroup) view);
            }
        }
    }

    protected final void scheduleViewGroupSkin(ViewGroup group) {
        if (group != null) {
            final int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = group.getChildAt(i);
                scheduleViewSkin(child);
            }
        }
    }

    protected final void scheduleViewFont(View view) {
        if (view != null) {
            if (view instanceof EnvCallback) {
                ((EnvCallback) view).scheduleFont();
            } else if (view instanceof ViewGroup) {
                scheduleViewGroupFont((ViewGroup) view);
            }
        }
    }

    protected final void scheduleViewGroupFont(ViewGroup group) {
        if (group != null) {
            final int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = group.getChildAt(i);
                scheduleViewFont(child);
            }
        }
    }
}
