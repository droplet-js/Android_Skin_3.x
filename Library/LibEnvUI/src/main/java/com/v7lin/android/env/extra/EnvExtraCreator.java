package com.v7lin.android.env.extra;

import android.content.Context;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public interface EnvExtraCreator<T, D> {
	
	public T createFrom(Context context, D data, D compare);

}
