package com.v7lin.android.env.extra;

import android.content.Context;

public interface EnvExtraCreator<T, D> {
	
	public T createFrom(Context context, D data, D compare);

}
