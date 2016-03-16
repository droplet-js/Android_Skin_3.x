package com.v7lin.android.env.widget;

import android.graphics.drawable.Drawable;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public interface XAbsListViewCall extends XViewGroupCall {

	public void scheduleSelector(Drawable sel);

	public void scheduleCacheColorHint(int color);
}
