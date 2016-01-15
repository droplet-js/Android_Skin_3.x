package com.v7lin.android.env;

import java.io.File;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import com.v7lin.android.env.font.FontFactory;
import com.v7lin.android.env.font.FontFamily;
import com.v7lin.android.env.impl.NullEnvSetup;
import com.v7lin.android.env.impl.NullSkinChecker;
import com.v7lin.android.env.skin.SkinFactory;
import com.v7lin.android.env.skin.SkinFamily;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public class EnvResManager {

	private EnvSetup mEnvSetup = NullEnvSetup.getInstance();
	private SkinChecker mSkinChecker = NullSkinChecker.getInstance();

	private SkinFamily mDefaultSkinFamily;
	private FontFamily mDefaultFontFamily;

	private EnvResManager() {
		super();
	}

	public void setEnvSetup(EnvSetup setup) {
		mEnvSetup = setup != null ? setup : NullEnvSetup.getInstance();
	}

	public void setSkinChecker(SkinChecker checker) {
		mSkinChecker = checker != null ? checker : NullSkinChecker.getInstance();
	}

	public SkinFamily getSkinFamily(Context context, Resources originalRes) {
		return getTopLevelSkinFamily(context, originalRes, getSkinPath(context));
	}

	public String getSkinPath(Context context) {
		return mEnvSetup.getSkinPath(context);
	}

	private boolean isSkinPathValid(Context context, String skinPath) {
		return mSkinChecker.isSkinValid(context, skinPath);
	}

	public boolean isSkinChanged(Context context, String compareSkinPath) {
		String skinPath = getSkinPath(context);
		return !TextUtils.equals(compareSkinPath, skinPath);
	}

	public synchronized SkinFamily getTopLevelSkinFamily(Context context, Resources originalRes, String skinPath) {
		SkinFamily skinFamily = null;
		if (!TextUtils.isEmpty(skinPath)) {
			if (isSkinPathValid(context, skinPath)) {
				skinFamily = EnvResCache.getInstance().getActiveSkinFamily(skinPath);
				if (!SkinFactory.isValid(skinFamily)) {
					skinFamily = SkinFactory.makeSkin(context, originalRes, skinPath);
					EnvResCache.getInstance().putActiveSkinFamily(skinPath, skinFamily);
				}
			} else {
				EnvResCache.getInstance().removeActiveSkinFamily(skinPath);
			}
		}
		if (skinFamily == null) {
			if (mDefaultSkinFamily == null) {
				mDefaultSkinFamily = new SkinFamily("", context.getPackageName(), originalRes, originalRes);
			}
			skinFamily = mDefaultSkinFamily;
		}
		return skinFamily;
	}

	public FontFamily getFontFamily(Context context) {
		return getTopLevelFontFamily(context, getFontPath(context));
	}

	public String getFontPath(Context context) {
		return mEnvSetup.getFontPath(context);
	}

	private boolean isFontPathValid(Context context, String fontPath) {
		boolean exist = false;
		if (!TextUtils.isEmpty(fontPath)) {
			File fontFile = new File(fontPath);
			exist = fontFile.exists() && fontFile.isFile();
		}
		return exist;
	}

	public boolean isFontChanged(Context context, String compareFontPath) {
		String fontPath = getFontPath(context);
		return !TextUtils.equals(compareFontPath, fontPath);
	}

	public synchronized FontFamily getTopLevelFontFamily(Context context, String fontPath) {
		FontFamily fontFamily = null;
		if (!TextUtils.isEmpty(fontPath)) {
			if (isFontPathValid(context, fontPath)) {
				fontFamily = EnvResCache.getInstance().getActiveFontFamily(fontPath);
				if (!FontFactory.isValid(fontFamily)) {
					fontFamily = FontFactory.makeFont(context, fontPath);
					EnvResCache.getInstance().putActiveFontFamily(fontPath, fontFamily);
				}
			} else {
				EnvResCache.getInstance().removeActiveFontFamily(fontPath);// 删除失效皮肤
			}
		}
		if (fontFamily == null) {
			if (mDefaultFontFamily == null) {
				mDefaultFontFamily = FontFamily.DEFAULT_FONT;
			}
			fontFamily = mDefaultFontFamily;
		}
		return fontFamily;
	}

	private static class EnvResManagerHolder {
		private static final EnvResManager INSTANCE = new EnvResManager();
	}

	public static EnvResManager getGlobal() {
		return EnvResManagerHolder.INSTANCE;
	}
}
