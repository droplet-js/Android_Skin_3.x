package com.v7lin.android.env.impl;

import android.content.Context;

import com.v7lin.android.env.SkinChecker;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public class SimpleSkinChecker implements SkinChecker {

	public static final SimpleSkinChecker NOT_ACCEPTED = new SimpleSkinChecker(false);

	private boolean mIsAccepted;

	public SimpleSkinChecker(boolean isAccepted) {
		super();
		mIsAccepted = isAccepted;
	}

	@Override
	public boolean isSkinValid(Context context, String skinPath) {
		return mIsAccepted;
	}

}
