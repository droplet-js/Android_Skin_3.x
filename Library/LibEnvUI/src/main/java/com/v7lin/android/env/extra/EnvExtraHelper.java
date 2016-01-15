package com.v7lin.android.env.extra;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.v7lin.android.env.EnvResBridge;
import com.v7lin.android.env.EnvResManager;
import com.v7lin.android.env.font.FontFamily;
import com.v7lin.android.env.skin.SkinFamily;

/**
 * @author v7lin Email:v7lin@qq.com
 */
public class EnvExtraHelper {

	public static <FontData> ArrayList<FontData> loadFontDatas(Context context, EnvResBridge bridge, File fontDir, FilenameFilter fontFilter, EnvExtraCreator<FontData, FontFamily> creator) {
		ArrayList<FontData> fontDatas = new ArrayList<FontData>();

		// 当前字体
		FontFamily currFontFamily = bridge.getFontFamily();

		// 读取默认字体参数
		FontFamily defaultFontFamily = FontFamily.DEFAULT_FONT;
		fontDatas.add(creator.createFrom(context, defaultFontFamily, currFontFamily));

		// 读取font文件夹下字体参数
		File[] fontFiles = fontDir.listFiles(fontFilter);
		if (fontFiles != null && fontFiles.length > 0) {
			for (File fontFile : fontFiles) {
				String fontPath = fontFile.getAbsolutePath();
				FontFamily fontFamily = EnvResManager.getGlobal().getTopLevelFontFamily(context, fontPath);
				fontDatas.add(creator.createFrom(context, fontFamily, currFontFamily));
			}
		}

		return fontDatas;
	}

	public static <SkinData> List<SkinData> loadSkinDatas(Context context, EnvResBridge bridge, File skinDir, FilenameFilter skinFilter, EnvExtraCreator<SkinData, SkinFamily> creator) {
		ArrayList<SkinData> skinDatas = new ArrayList<SkinData>();

		// 当前皮肤
		SkinFamily currSkinFamily = bridge.getSkinFamily();

		// 读取默认皮肤参数
		SkinFamily defaultSkinFamily = new SkinFamily("", context.getPackageName(), bridge.getOriginalRes(), bridge.getOriginalRes());
		skinDatas.add(creator.createFrom(context, defaultSkinFamily, currSkinFamily));

		// 读取skin文件夹下皮肤参数
		File[] skinFiles = skinDir.listFiles(skinFilter);
		if (skinFiles != null && skinFiles.length > 0) {
			for (File skinFile : skinFiles) {
				String skinPath = skinFile.getAbsolutePath();
				SkinFamily skinFamily = EnvResManager.getGlobal().getTopLevelSkinFamily(context, bridge.getOriginalRes(), skinPath);
				skinDatas.add(creator.createFrom(context, skinFamily, currSkinFamily));
			}
		}

		return skinDatas;
	}

}
