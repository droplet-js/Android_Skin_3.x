package com.v7lin.android.env;

import android.content.Context;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public interface SystemResMap {

	public int mapping(Context context, int resid, String resourcePackageName, String resourceTypeName, String resourceEntryName);
}
