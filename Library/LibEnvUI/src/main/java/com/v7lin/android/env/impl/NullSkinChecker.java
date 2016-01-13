package com.v7lin.android.env.impl;

import android.content.Context;

import com.v7lin.android.env.SkinChecker;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public class NullSkinChecker implements SkinChecker {

	private NullSkinChecker() {
		super();
	}

	@Override
	public boolean isSkinValid(Context context, String skinPath) {
		return false;
	}
	
	private static class NullSkinCheckerHolder {
		private static final NullSkinChecker INSTANCE = new NullSkinChecker();
	}

	public static NullSkinChecker getInstance() {
		return NullSkinCheckerHolder.INSTANCE;
	}

}
