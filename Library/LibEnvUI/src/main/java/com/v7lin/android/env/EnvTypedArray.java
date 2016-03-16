package com.v7lin.android.env;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class EnvTypedArray {

	private final Context mContext;
	private final Resources mOriginalRes;
	private final TypedArray mWrapped;

	private EnvTypedArray(Context context, Resources originalRes, TypedArray wrapped) {
		super();
		mContext = context;
		mOriginalRes = originalRes;
		mWrapped = wrapped;
	}

	public EnvRes getEnvRes(int index, boolean allowSysRes) {
		return getEnvRes(index, 0, null, allowSysRes);
	}

	public EnvRes getEnvRes(int index, int defValue, boolean allowSysRes) {
		return getEnvRes(index, defValue, null, allowSysRes);
	}

	public EnvRes getEnvRes(int index, EnvRes defEnvRes, boolean allowSysRes) {
		return getEnvRes(index, 0, defEnvRes, allowSysRes);
	}
	
	public EnvRes getEnvRes(int index, int defValue, EnvRes defEnvRes, boolean allowSysRes) {
		if (mWrapped.hasValue(index)) {
			EnvRes res = new EnvRes(mWrapped.getResourceId(index, defValue));
			return res.isValid(mContext, mOriginalRes, allowSysRes) ? res : null;
		}
		return defEnvRes;
	}

	public void recycle() {
		mWrapped.recycle();
	}
	
	public static EnvTypedArray obtainStyledAttributes(Context context, Resources originalRes, int[] attrs) {
		TypedArray wrapped = context.obtainStyledAttributes(attrs);
        return new EnvTypedArray(context, originalRes, wrapped);
    }
	
	public static EnvTypedArray obtainStyledAttributes(Context context, Resources originalRes, int resid, int[] attrs) {
		TypedArray wrapped = context.obtainStyledAttributes(resid, attrs);
		return new EnvTypedArray(context, originalRes, wrapped);
	}

	public static EnvTypedArray obtainStyledAttributes(Context context, Resources originalRes, AttributeSet set, int[] attrs) {
		TypedArray wrapped = context.obtainStyledAttributes(set, attrs);
		return new EnvTypedArray(context, originalRes, wrapped);
	}

	public static EnvTypedArray obtainStyledAttributes(Context context, Resources originalRes, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes) {
		TypedArray wrapped = context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
		return new EnvTypedArray(context, originalRes, wrapped);
	}
}
