package com.v7lin.android.env.widget;

import java.util.Arrays;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvTextViewChanger<TV extends TextView, TVC extends XTextViewCall> extends EnvViewChanger<TV, TVC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.drawableLeft,
            //
            android.R.attr.drawableTop,
            //
            android.R.attr.drawableRight,
            //
            android.R.attr.drawableBottom,
            //
            android.R.attr.textAppearance,
            //
            android.R.attr.textColorHighlight,
            //
            android.R.attr.textColor,
            //
            android.R.attr.textColorHint,
            //
            android.R.attr.textColorLink,
            //
            android.R.attr.text
    };

    private static final int[] ATTRS_TEXT = {
            //
            android.R.attr.textColorHighlight,
            //
            android.R.attr.textColor,
            //
            android.R.attr.textColorHint,
            //
            android.R.attr.textColorLink
    };

    static {
        Arrays.sort(ATTRS);
        Arrays.sort(ATTRS_TEXT);
    }

    private EnvRes mDrawableLeftEnvRes;
    private EnvRes mDrawableTopEnvRes;
    private EnvRes mDrawableRightEnvRes;
    private EnvRes mDrawableBottomEnvRes;

    private EnvRes mTextColorHighlightEnvRes;
    private EnvRes mTextColorEnvRes;
    private EnvRes mTextColorHintEnvRes;
    private EnvRes mTextColorLinkEnvRes;

    public EnvTextViewChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);
        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), getOriginalRes(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mDrawableLeftEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.drawableLeft), allowSysRes);
        mDrawableTopEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.drawableTop), allowSysRes);
        mDrawableRightEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.drawableRight), allowSysRes);
        mDrawableBottomEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.drawableBottom), allowSysRes);

        EnvRes textAppearanceEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.textAppearance), allowSysRes);
        if (textAppearanceEnvRes != null) {
            EnvTypedArray textAppearanceArray = EnvTypedArray.obtainStyledAttributes(getContext(), getOriginalRes(), textAppearanceEnvRes.getResid(), ATTRS_TEXT);

            mTextColorHighlightEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColorHighlight), allowSysRes);
            mTextColorEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColor), allowSysRes);
            mTextColorHintEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColorHint), allowSysRes);
            mTextColorLinkEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColorLink), allowSysRes);

            textAppearanceArray.recycle();
        }

        mTextColorHighlightEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.textColorHighlight), mTextColorHighlightEnvRes, allowSysRes);
        mTextColorEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.textColor), mTextColorEnvRes, allowSysRes);
        mTextColorHintEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.textColorHint), mTextColorHintEnvRes, allowSysRes);
        mTextColorLinkEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.textColorLink), mTextColorLinkEnvRes, allowSysRes);

        array.recycle();
    }

    @Override
    protected void onApplyAttr(TV view, TVC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.textAppearance: {
                EnvTypedArray textAppearanceArray = EnvTypedArray.obtainStyledAttributes(getContext(), getOriginalRes(), resid, ATTRS_TEXT);

                mTextColorHighlightEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColorHighlight), mTextColorHighlightEnvRes, allowSysRes);
                mTextColorEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColor), mTextColorEnvRes, allowSysRes);
                mTextColorHintEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColorHint), mTextColorHintEnvRes, allowSysRes);
                mTextColorLinkEnvRes = textAppearanceArray.getEnvRes(Arrays.binarySearch(ATTRS_TEXT, android.R.attr.textColorLink), mTextColorLinkEnvRes, allowSysRes);

                textAppearanceArray.recycle();

//                scheduleTextColor(view, call);
                break;
            }
            case android.R.attr.drawableLeft: {
                EnvRes res = new EnvRes(resid);
                mDrawableLeftEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//                scheduleCompoundDrawable(view, call);
                break;
            }
            case android.R.attr.drawableTop: {
                EnvRes res = new EnvRes(resid);
                mDrawableTopEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//                scheduleCompoundDrawable(view, call);
                break;
            }
            case android.R.attr.drawableRight: {
                EnvRes res = new EnvRes(resid);
                mDrawableRightEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//                scheduleCompoundDrawable(view, call);
                break;
            }
            case android.R.attr.drawableBottom: {
                EnvRes res = new EnvRes(resid);
                mDrawableBottomEnvRes = res.isValid(getContext(), getOriginalRes(), allowSysRes) ? res : null;

//                scheduleCompoundDrawable(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(TV view, TVC call) {
        super.onScheduleSkin(view, call);
        scheduleCompoundDrawable(view, call);
        scheduleTextColor(view, call);
    }

    @Override
    protected void onScheduleFont(TV view, TVC call) {
        super.onScheduleFont(view, call);
        view.setTypeface(getTypeface());
    }

    private void scheduleCompoundDrawable(TV view, TVC call) {
        if (mDrawableLeftEnvRes != null || mDrawableTopEnvRes != null || mDrawableRightEnvRes != null || mDrawableBottomEnvRes != null) {
            Drawable[] compoundDrawables = view.getCompoundDrawables();
            Drawable drawableLeft = mDrawableLeftEnvRes != null ? getDrawable(mDrawableLeftEnvRes.getResid()) : compoundDrawables[0];
            Drawable drawableTop = mDrawableTopEnvRes != null ? getDrawable(mDrawableTopEnvRes.getResid()) : compoundDrawables[1];
            Drawable drawableRight = mDrawableRightEnvRes != null ? getDrawable(mDrawableRightEnvRes.getResid()) : compoundDrawables[2];
            Drawable drawableBottom = mDrawableBottomEnvRes != null ? getDrawable(mDrawableBottomEnvRes.getResid()) : compoundDrawables[3];
            if (drawableLeft != null || drawableTop != null || drawableRight != null || drawableBottom != null) {
                EnvRes[] compoundResCache = new EnvRes[4];
                compoundResCache[0] = mDrawableLeftEnvRes;
                compoundResCache[1] = mDrawableTopEnvRes;
                compoundResCache[2] = mDrawableRightEnvRes;
                compoundResCache[3] = mDrawableBottomEnvRes;

                final int drawablePadding = view.getCompoundDrawablePadding();
                call.scheduleCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
                view.setCompoundDrawablePadding(drawablePadding);

                mDrawableLeftEnvRes = compoundResCache[0];
                mDrawableTopEnvRes = compoundResCache[1];
                mDrawableRightEnvRes = compoundResCache[2];
                mDrawableBottomEnvRes = compoundResCache[3];
            }
        }
    }

    private void scheduleTextColor(TV view, TVC call) {
        if (mTextColorHighlightEnvRes != null) {
            int textColorHighlight = getColor(mTextColorHighlightEnvRes.getResid());
            call.scheduleHighlightColor(textColorHighlight);
        }
        if (mTextColorEnvRes != null) {
            ColorStateList textColor = getColorStateList(mTextColorEnvRes.getResid());
            call.scheduleTextColor(textColor);
        }
        if (mTextColorHintEnvRes != null) {
            ColorStateList textColorHint = getColorStateList(mTextColorHintEnvRes.getResid());
            call.scheduleHintTextColor(textColorHint);
        }
        if (mTextColorLinkEnvRes != null) {
            ColorStateList textColorLink = getColorStateList(mTextColorLinkEnvRes.getResid());
            call.scheduleLinkTextColor(textColorLink);
        }
    }
}
