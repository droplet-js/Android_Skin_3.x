package com.v7lin.android.env.skin;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
interface SkinCreator {
	
	public SkinFamily createFrom(Context context, Resources originalRes, String skinPath);

}
