package com.v7lin.android.env;

import android.view.View;

import com.v7lin.android.env.widget.EnvUIChanger;
import com.v7lin.android.env.widget.XViewCall;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public interface EnvCallback<UI extends View, UIC extends XViewCall> {

	public EnvUIChanger<UI, UIC> ensureEnvUIChanger(EnvResBridge bridge, boolean allowSysRes);

	public void scheduleSkin();

	public void scheduleFont();

}
