package com.v7lin.android.env.font;

import android.graphics.Typeface;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public class FontFamily {

	public static final FontFamily DEFAULT_FONT = new FontFamily("", "default", Typeface.DEFAULT);

	private String mFontPath;
	private String mFontName;
	private Typeface mTypeface;

	public FontFamily(String fontPath, String fontName, Typeface typeFace) {
		mFontPath = fontPath;
		mFontName = fontName;
		mTypeface = typeFace;
	}

	public String getFontPath() {
		return mFontPath;
	}

	public String getFontName() {
		return mFontName;
	}

	public Typeface getTypeface() {
		return mTypeface;
	}
}
