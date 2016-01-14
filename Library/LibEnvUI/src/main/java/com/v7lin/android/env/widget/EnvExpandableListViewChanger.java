package com.v7lin.android.env.widget;

import java.util.Arrays;

import com.v7lin.android.env.EnvRes;
import com.v7lin.android.env.EnvTypedArray;
import com.v7lin.android.env.EnvResBridge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvExpandableListViewChanger<ELV extends ExpandableListView, ELVC extends XExpandableListViewCall> extends EnvListViewChanger<ELV, ELVC> {

    private static final int[] ATTRS = {
            //
            android.R.attr.groupIndicator,
            //
            android.R.attr.childIndicator,
            //
            android.R.attr.childDivider
    };

    static {
        Arrays.sort(ATTRS);
    }

    private EnvRes mGroupIndicatorEnvRes;
    private EnvRes mChildIndicatorEnvRes;
    private EnvRes mChildDividerEnvRes;

    public EnvExpandableListViewChanger(Context context, EnvResBridge bridge, boolean allowSysRes) {
        super(context, bridge, allowSysRes);
    }

    @Override
    protected void onApplyStyle(AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean allowSysRes) {
        super.onApplyStyle(attrs, defStyleAttr, defStyleRes, allowSysRes);

        EnvTypedArray array = EnvTypedArray.obtainStyledAttributes(getContext(), attrs, ATTRS, defStyleAttr, defStyleRes);
        mGroupIndicatorEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.groupIndicator), allowSysRes);
        mChildIndicatorEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.childIndicator), allowSysRes);
        mChildDividerEnvRes = array.getEnvRes(Arrays.binarySearch(ATTRS, android.R.attr.childDivider), allowSysRes);
        array.recycle();
    }

    @Override
    protected void onApplyAttr(ELV view, ELVC call, int attr, int resid, boolean allowSysRes) {
        super.onApplyAttr(view, call, attr, resid, allowSysRes);
        switch (attr) {
            case android.R.attr.groupIndicator: {
                EnvRes res = new EnvRes(resid);
                mGroupIndicatorEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleGroupIndicator(view, call);
                break;
            }
            case android.R.attr.childIndicator: {
                EnvRes res = new EnvRes(resid);
                mChildIndicatorEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleChildIndicator(view, call);
                break;
            }
            case android.R.attr.childDivider: {
                EnvRes res = new EnvRes(resid);
                mChildDividerEnvRes = res.isValid(getContext(), allowSysRes) ? res : null;

//                scheduleChildDivider(view, call);
                break;
            }
        }
    }

    @Override
    protected void onScheduleSkin(ELV view, ELVC call) {
        super.onScheduleSkin(view, call);
        scheduleGroupIndicator(view, call);
        scheduleChildIndicator(view, call);
        scheduleChildDivider(view, call);
    }

    private void scheduleGroupIndicator(ELV view, ELVC call) {
        if (mGroupIndicatorEnvRes != null) {
            Drawable drawable = getDrawable(mGroupIndicatorEnvRes.getResid());
            if (drawable != null) {
                call.scheduleGroupIndicator(drawable);
            }
        }
    }

    private void scheduleChildIndicator(ELV view, ELVC call) {
        if (mChildIndicatorEnvRes != null) {
            Drawable drawable = getDrawable(mChildIndicatorEnvRes.getResid());
            if (drawable != null) {
                call.scheduleChildIndicator(drawable);
            }
        }
    }

    private void scheduleChildDivider(ELV view, ELVC call) {
        if (mChildDividerEnvRes != null) {
            Drawable drawable = getDrawable(mChildDividerEnvRes.getResid());
            if (drawable != null) {
                call.scheduleChildDivider(drawable);
            }
        }
    }
}
