package com.v7lin.android.env.widget;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvViewChanger<V extends View, VC extends XViewCall> extends EnvUIChanger<V, VC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.background,
            //
            android.R.attr.foreground,
            //
            android.R.attr.minWidth,
            //
            android.R.attr.minHeight,
            //
            android.R.attr.scrollbarTrackHorizontal,
            //
            android.R.attr.scrollbarThumbHorizontal,
            //
            android.R.attr.scrollbarTrackVertical,
            //
            android.R.attr.scrollbarThumbVertical
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mBackgroundEnvRes;
    private EnvRes mForegroundEnvRes;

    private EnvRes mMinWidthEnvRes;
    private EnvRes mMinHeightEnvRes;

    private EnvRes mScrollbarTrackHorizontalEnvRes;
    private EnvRes mScrollbarThumbHorizontalEnvRes;
    private EnvRes mScrollbarTrackVerticalEnvRes;
    private EnvRes mScrollbarThumbVerticalEnvRes;

    public EnvViewChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mBackgroundEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.background), allowSysRes);
        mForegroundEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.foreground), allowSysRes);

        mMinWidthEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.minWidth), allowSysRes);
        mMinHeightEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.minHeight), allowSysRes);

        mScrollbarTrackHorizontalEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.scrollbarTrackHorizontal), allowSysRes);
        mScrollbarThumbHorizontalEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.scrollbarThumbHorizontal), allowSysRes);
        mScrollbarTrackVerticalEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.scrollbarTrackVertical), allowSysRes);
        mScrollbarThumbVerticalEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.scrollbarThumbVertical), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(V view, VC call, int attr, int resid, boolean allowSysRes) {
        switch (attr) {
            case android.R.attr.background: {
                EnvRes res = new EnvRes(resid);
                mBackgroundEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

                scheduleBackground(view, call);
                break;
            }
            case android.R.attr.foreground: {
                EnvRes res = new EnvRes(resid);
                mForegroundEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

                scheduleForeground(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(V view, VC call) {
        scheduleBackground(view, call);
        scheduleForeground(view, call);
        scheduleMinParams(view, call);
        scheduleScrollBar(view, call);
    }

    private void scheduleBackground(V view, VC call) {
        if (mBackgroundEnvRes != null) {
            Drawable drawable = getDrawable(mBackgroundEnvRes.getResid());
            if (drawable != null) {
                final int leftPadding = view.getPaddingLeft();
                final int topPadding = view.getPaddingTop();
                final int rightPadding = view.getPaddingRight();
                final int bottomPadding = view.getPaddingBottom();
                call.scheduleBackgroundDrawable(drawable);
                view.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
            }
        }
    }

    private void scheduleForeground(V view, VC call) {
        if (mForegroundEnvRes != null) {
            Drawable drawable = getDrawable(mForegroundEnvRes.getResid());
            if (drawable != null) {
                call.scheduleForeground(drawable);
            }
        }
    }

    private void scheduleMinParams(V view, VC call) {
        if (mMinWidthEnvRes != null) {
            final int minWidth = getDimensionPixelSize(mMinWidthEnvRes.getResid());
            view.setMinimumWidth(minWidth);
        }
        if (mMinHeightEnvRes != null) {
            final int minHeight = getDimensionPixelSize(mMinHeightEnvRes.getResid());
            view.setMinimumHeight(minHeight);
        }
    }

    private void scheduleScrollBar(V view, VC call) {
        try {
            if (mScrollbarTrackHorizontalEnvRes != null || mScrollbarThumbHorizontalEnvRes != null || mScrollbarTrackVerticalEnvRes != null || mScrollbarThumbVerticalEnvRes != null) {
                Field mScrollCacheField = View.class.getDeclaredField("mScrollCache");
                mScrollCacheField.setAccessible(true);
                Object mScrollCache = mScrollCacheField.get(view);
                if (mScrollCache != null) {
                    Field scrollBarField = mScrollCache.getClass().getDeclaredField("scrollBar");
                    scrollBarField.setAccessible(true);
                    Object scrollBar = scrollBarField.get(mScrollCache);
                    if (scrollBar != null) {
                        if (mScrollbarTrackHorizontalEnvRes != null) {
                            Drawable horizontalTrackDrawable = getDrawable(mScrollbarTrackHorizontalEnvRes.getResid());
                            if (horizontalTrackDrawable != null) {
                                Method setHorizontalTrackDrawableMethod = scrollBar.getClass().getDeclaredMethod("setHorizontalTrackDrawable", Drawable.class);
                                setHorizontalTrackDrawableMethod.setAccessible(true);
                                setHorizontalTrackDrawableMethod.invoke(scrollBar, horizontalTrackDrawable);
                            }
                        }
                        if (mScrollbarThumbHorizontalEnvRes != null) {
                            Drawable horizontalThumbDrawable = getDrawable(mScrollbarThumbHorizontalEnvRes.getResid());
                            if (horizontalThumbDrawable != null) {
                                Method setHorizontalThumbDrawableMethod = scrollBar.getClass().getDeclaredMethod("setHorizontalThumbDrawable", Drawable.class);
                                setHorizontalThumbDrawableMethod.setAccessible(true);
                                setHorizontalThumbDrawableMethod.invoke(scrollBar, horizontalThumbDrawable);
                            }
                        }
                        if (mScrollbarTrackVerticalEnvRes != null) {
                            Drawable verticalTrackDrawable = getDrawable(mScrollbarTrackVerticalEnvRes.getResid());
                            if (verticalTrackDrawable != null) {
                                Method setVerticalTrackDrawableMethod = scrollBar.getClass().getDeclaredMethod("setVerticalTrackDrawable", Drawable.class);
                                setVerticalTrackDrawableMethod.setAccessible(true);
                                setVerticalTrackDrawableMethod.invoke(scrollBar, verticalTrackDrawable);
                            }
                        }
                        if (mScrollbarThumbVerticalEnvRes != null) {
                            Drawable verticalThumbDrawable = getDrawable(mScrollbarThumbVerticalEnvRes.getResid());
                            if (verticalThumbDrawable != null) {
                                Method setVerticalThumbDrawableMethod = scrollBar.getClass().getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
                                setVerticalThumbDrawableMethod.setAccessible(true);
                                setVerticalThumbDrawableMethod.invoke(scrollBar, verticalThumbDrawable);
                            }
                        }
                    }
                }
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onScheduleFont(V view, VC call) {

    }
}
