package com.v7lin.android.os.env;

import java.io.File;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.v7lin.android.os.storage.StorageUtils;

/**
 * 
 * 
 * @author v7lin E-mail:v7lin@qq.com
 */
public final class PathUtils implements PathConst {
	
	private static final int STORAGE_INDEX = 0;

	private static String sRtlPath = PATH_APP_DIR;

	private PathUtils() {
		super();
	}

	public static void setAppRtlPath(String rtlPath) {
		sRtlPath = rtlPath;
	}

	private static String getAppRtlPath() {
		return !TextUtils.isEmpty(sRtlPath) ? sRtlPath : PATH_APP_DIR;
	}

	private static File makeDir(Context context, int index, boolean isPrivate) {
		File storageDir = StorageUtils.getStorageDir(context, index, isPrivate);
		File appDir = new File(storageDir, PathUtils.getAppRtlPath());
		if (!appDir.exists()) {
			appDir.mkdirs();
		}
		return appDir;
	}

	private static File makeSubDir(Context context, int index, boolean isPrivate, String subPath) {
		File appDir = PathUtils.makeDir(context, index, isPrivate);
		File subDir = new File(appDir, subPath);
		if (!subDir.exists()) {
			subDir.mkdirs();
		}
		return subDir;
	}
	
	/**
	 * 为了测试方便，暂时先放在SD卡上
	 * 
	 * 这个需要保护起来，避免用户误删
	 */
	public static File getSkinDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_SKIN);
	}

	public static File getFontDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_FONT);
	}

	public static File getCrashDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_CRASH);
	}

	public static File getCacheDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_CACHE);
	}

	public static File getDataBasesDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_DATABASES);
	}

	public static File getSharedPrefsDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_SHARED_PREFS);
	}

	/**
	 * Android 4.1.2 Api 中有对 Dex 做了限制，Dex 文件只能放在私有目录下
	 */
	public static File getDexDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN, PATH_SUB_DIR_DEX);
	}

	/**
	 * 为了测试方便，暂时先放在SD卡上
	 * 
	 * 这个需要保护起来，避免用户误删
	 */
	public static File getDexAppDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_DEX_APP);
	}

	public static File getAppDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_APP);
	}
	
	public static File getBookDir(Context context) {
		return PathUtils.makeSubDir(context, STORAGE_INDEX, false, PATH_SUB_DIR_BOOK);
	}
}
