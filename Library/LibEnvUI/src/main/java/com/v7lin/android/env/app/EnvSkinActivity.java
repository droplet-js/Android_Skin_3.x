package com.v7lin.android.env.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.v7lin.android.app.SuperActivity;
import com.v7lin.android.env.EnvResBridge;
import com.v7lin.android.env.EnvResManager;
import com.v7lin.android.env.SystemResMap;
import com.v7lin.android.env.EnvCallback;
import com.v7lin.android.env.widget.EnvUIChanger;
import com.v7lin.android.env.widget.EnvActivityChanger;
import com.v7lin.android.env.widget.EnvViewMap;
import com.v7lin.android.env.widget.EnvViewParams;
import com.v7lin.android.env.widget.XActivityCall;
import com.v7lin.android.env.widget.XViewCall;

/**
 *
 *
 * @author v7lin E-mail:v7lin@qq.com
 */
public class EnvSkinActivity extends SuperActivity implements XActivityCall {

	private static final String[] sClassPrefixList = {
			// widget
			"android.widget.",
			// webkit
			"android.webkit.",
			// view
			"android.view.",
			// app
			"android.app."
	};

	private EnvResBridge mEnvResBridge;

	private EnvUIChanger<Activity, XActivityCall> mEnvUIChanger;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(newBase);
		ensureEnvUIBridge();
	}

	private void ensureEnvUIBridge() {
		if (mEnvResBridge == null) {
			mEnvResBridge = new EnvResBridge(getBaseContext(), getResources(), EnvResManager.getGlobal());
		}
	}

	public EnvResBridge getEnvResBridge() {
		return mEnvResBridge;
	}

	public void setSystemResMap(SystemResMap systemResMap) {
		if (mEnvResBridge != null) {
			mEnvResBridge.setSystemResMap(systemResMap);
		}
	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		View view = super.onCreateView(name, context, attrs);
		if (view == null) {
			String transferName = EnvViewMap.getInstance().transfer(name);
			if (transferName.indexOf(".") > -1) {
				LayoutInflater inflater = LayoutInflater.from(this);
				try {
					view = inflater.createView(transferName, null, attrs);
				} catch (ClassNotFoundException e) {
					// In this case we want to let the base class take a crack at it.
				}
			}

			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(this);
				for (String prefix : sClassPrefixList) {
					try {
						view = inflater.createView(name, prefix, attrs);
						if (view != null) {
							break;
						}
					} catch (ClassNotFoundException e) {
						// In this case we want to let the base class take a crack at it.
					}
				}
			}
		}
		if (view != null && view instanceof EnvCallback) {
			EnvCallback callback = (EnvCallback) view;
			EnvViewParams params = EnvViewMap.getInstance().obtainViewParams(view.getClass());
			if (params != null) {
				EnvUIChanger<View, XViewCall> changer = callback.ensureEnvUIChanger(mEnvResBridge, params.allowSysRes);
				changer.applyStyle(attrs, params.defStyleAttr, params.defStyleRes, view.isInEditMode());
			}
		}
		return view;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mEnvUIChanger = new EnvActivityChanger<Activity, XActivityCall>(this, mEnvResBridge, false);
		mEnvUIChanger.applyStyle(null, 0, 0, false);
		mEnvUIChanger.scheduleSkin(this, this, false);
		mEnvUIChanger.scheduleFont(this, this, false);
	}

	/**
	 * 可继承 {@link EnvSkinActivity} 重写该函数
	 * 
	 * 设置视图资源，实现不支持换肤功能的视图，进行换肤
	 */
	public void scheduleSkin() {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleSkin(this, this, false);
		}
	}

	/**
	 * 可继承 {@link EnvSkinActivity} 重写该函数
	 * 
	 * 设置视图字体，实现不支持换字体功能的视图，进行换字体
	 */
	public void scheduleFont() {
		if (mEnvUIChanger != null) {
			mEnvUIChanger.scheduleFont(this, this, false);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		scheduleSkin();
		scheduleFont();
	}

	@Override
	public void scheduleBackgroundDrawable(Drawable background) {
		getWindow().setBackgroundDrawable(background);
	}
}
