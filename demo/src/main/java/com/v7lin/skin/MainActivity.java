package com.v7lin.skin;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.view.View;
import android.widget.TextView;

import com.v7lin.android.env.extra.EnvExtraHelper;
import com.v7lin.android.env.extra.SkinFilter;
import com.v7lin.android.env.impl.SharedPrefSetup;
import com.v7lin.android.env.skin.SkinFamily;
import com.v7lin.android.os.env.PathUtils;
import com.v7lin.android.support.app.SkinData;
import com.v7lin.android.support.app.SkinExtraCreator;
import com.v7lin.android.support.app.SupportActivity;

import java.util.List;
import java.util.Random;

public class MainActivity extends SupportActivity {

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        请先将 {Project}/Library/LibSkin/skin 下的所有 ***.skin 拷贝到手机设备存储（或SD卡存储）中的 /v7lin/skin 文件夹下，并注释这句话。

        SkinExtraCreator skinExtraCreator = new SkinExtraCreator();
        final List<SkinData> skinDatas = EnvExtraHelper.loadSkinDatas(this, getEnvResBridge(), PathUtils.getSkinDir(this), SkinFilter.DEFAULT_SKIN_FILTER, skinExtraCreator);

        SkinFamily currSkinFamily = getEnvResBridge().getSkinFamily();
        SkinData currSkinData = skinExtraCreator.createFrom(this, currSkinFamily, currSkinFamily);

        final TextView changeSkin = (TextView) findViewById(R.id.change_skin);
        changeSkin.setText(currSkinData.getSkinName());
        changeSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinData data = skinDatas.get(new Random().nextInt(skinDatas.size()));
                SharedPrefSetup.getGlobal().setSkinPath(MainActivity.this, data.getSkinPath());
                changeSkin.setText(data.getSkinName());
                scheduleSkin();
            }
        });

        findViewById(R.id.change_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSkin.setBackgroundResource(i++ % 2 == 1 ? R.color.primary : R.color.primary_dark);
            }
        });
    }
}
