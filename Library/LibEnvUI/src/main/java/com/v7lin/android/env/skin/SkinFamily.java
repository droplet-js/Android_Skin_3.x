package com.v7lin.android.env.skin;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.v7lin.android.env.EnvRes;

/**
 * @author v7lin Email:v7lin@qq.com
 */
@SuppressWarnings("deprecation")
public class SkinFamily {

	private String mSkinPath;
	private String mSkinPkg;
	private Resources mSkinRes;
	private Resources mOriginalRes;

	public SkinFamily(String skinPath, String skinPkg, Resources skinRes, Resources original) {
		super();
		mSkinPath = skinPath;
		mSkinPkg = skinPkg;
		mSkinRes = skinRes;
		mOriginalRes = original;
	}

	public String getSkinPath() {
		return mSkinPath;
	}

	Resources getSkinRes() {
		return mSkinRes;
	}

	private EnvRes mappingSkinRes(int id) {
		final String typeName = mOriginalRes.getResourceTypeName(id);
		final String entryName = mOriginalRes.getResourceEntryName(id);
//		Log.e("TAG", "typeName: " + typeName + "; entryName: " + entryName + ".");
		final int mappingid = mSkinRes.getIdentifier(entryName, typeName, mSkinPkg/*packageName*/);
		return new EnvRes(mappingid);
	}

	public boolean hasValue(int id) {
		EnvRes mapping = mappingSkinRes(id);
		return mapping != null && mapping.isValid();
	}

	public CharSequence getText(int id) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			return mSkinRes.getText(mapping.getResid());
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}

	public float getDimension(int id) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			return mSkinRes.getDimension(mapping.getResid());
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}

	public int getDimensionPixelOffset(int id) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			return mSkinRes.getDimensionPixelOffset(mapping.getResid());
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}

	public int getDimensionPixelSize(int id) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			return mSkinRes.getDimensionPixelSize(mapping.getResid());
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}

	public Drawable getDrawable(int id, Resources.Theme theme) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				return mSkinRes.getDrawable(mapping.getResid(), theme);
			} else {
				return mSkinRes.getDrawable(mapping.getResid());
			}
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
	public Drawable getDrawableForDensity(int id, int density, Resources.Theme theme) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				return mSkinRes.getDrawableForDensity(mapping.getResid(), density, theme);
			} else {
				return mSkinRes.getDrawableForDensity(mapping.getResid(), density);
			}
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}

	public int getColor(int id, Resources.Theme theme) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				return mSkinRes.getColor(mapping.getResid(), theme);
			} else {
				return mSkinRes.getColor(mapping.getResid());
			}
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}

	public ColorStateList getColorStateList(int id, Resources.Theme theme) throws Resources.NotFoundException {
		EnvRes mapping = mappingSkinRes(id);
		if (mapping != null && mapping.isValid()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				return mSkinRes.getColorStateList(mapping.getResid(), theme);
			} else {
				return mSkinRes.getColorStateList(mapping.getResid());
			}
		} else {
			throw new Resources.NotFoundException("Resource(Skin) is not valid.");
		}
	}
}
