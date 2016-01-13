package com.v7lin.android.support.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.v7lin.android.env.SystemResMap;
import com.v7lin.android.env.app.EnvSkinActivity;
import com.v7lin.android.support.R;

import java.lang.reflect.Method;

/**
 * 
 * 
 * @author v7lin Email:v7lin@qq.com
 */
public class SupportActivity extends EnvSkinActivity implements SystemResMap {

	private static String sNavBarOverride;
	static {
		// Android allows a system property to override the presence of the
		// navigation bar.
		// Used by the emulator.
		// See
		// https://github.com/android/platform_frameworks_base/blob/master/policy/src/com/android/internal/policy/impl/PhoneWindowManager.java#L1076
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			try {
				Class<?> c = Class.forName("android.os.SystemProperties");
				Method m = c.getDeclaredMethod("get", String.class);
				m.setAccessible(true);
				sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
			} catch (Throwable e) {
				sNavBarOverride = null;
			}
		}
	}

	/**
	 * 双击退出应用时间间隔
	 */
	private static final long DURATION_DOUBLE_EXIT = 2 * 1000;

	private boolean mHasNavigationBar = false;
	private boolean mStatusBarAvailable = false;
	private boolean mNavigationBarAvailable = false;

	private long mLastExitTime;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(newBase);
		checkDeviceEnv();
		setSystemResMap(this);
	}

	/**
	 * 校验Android机器环境
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void checkDeviceEnv() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			// check device properties
			int showNavBarCFGResID = getResources().getIdentifier("config_showNavigationBar", "bool", "android");
			if (showNavBarCFGResID > 0) {
				mHasNavigationBar = getResources().getBoolean(showNavBarCFGResID);
				if (TextUtils.equals(sNavBarOverride, "1")) {
					mHasNavigationBar = false;
				} else if (TextUtils.equals(sNavBarOverride, "0")) {
					mHasNavigationBar = true;
				}
			} else {
				mHasNavigationBar = !ViewConfiguration.get(this).hasPermanentMenuKey();
			}
		}
	}

	/**
	 * 校验Android系统版本
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void checkPlatformEnv() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			final int[] attrs = { android.R.attr.windowTranslucentStatus, android.R.attr.windowTranslucentNavigation };
			TypedArray array = getTheme().obtainStyledAttributes(attrs);
			mStatusBarAvailable = array.getBoolean(0, false);
			Log.e("TAG", "StatusBarAvailable: " + mStatusBarAvailable);
			mNavigationBarAvailable = array.getBoolean(1, false);
			array.recycle();
		}
	}

	@Override
	public int mapping(Context context, int resid, String resourcePackageName, String resourceTypeName, String resourceEntryName) {
		if (resid == R.dimen.status_bar_height && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && mStatusBarAvailable) {
			return context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		}
		if (resid == R.dimen.navigation_bar_height && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && mHasNavigationBar && mNavigationBarAvailable) {
			return context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		}
		if (resid == R.dimen.navigation_bar_height_landscape && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && mHasNavigationBar && mNavigationBarAvailable) {
			return context.getResources().getIdentifier("navigation_bar_height_landscape", "dimen", "android");
		}
		if (resid == R.dimen.navigation_bar_width && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && mHasNavigationBar && mNavigationBarAvailable) {
			return context.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
		}
		return -1;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		checkPlatformEnv();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (TextUtils.equals(getIntent().getAction(), Intent.CATEGORY_LAUNCHER)) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				if ((System.currentTimeMillis() - mLastExitTime) > DURATION_DOUBLE_EXIT) {
					exitTips();
					mLastExitTime = System.currentTimeMillis();
					return true;
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		hideKeyboard();
		super.onBackPressed();
	}

	private void hideKeyboard() {
		IBinder windowToken = getWindow().getDecorView().getWindowToken();
		if (windowToken != null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	private void exitTips() {
		try {
			ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), 0);
			final String formatArgs = String.valueOf(info.loadLabel(getPackageManager()));
			final String text = getResources().getString(R.string.exit_tips_format, formatArgs);
			Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
