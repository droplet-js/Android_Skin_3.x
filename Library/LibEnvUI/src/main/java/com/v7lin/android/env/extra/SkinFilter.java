package com.v7lin.android.env.extra;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

/**
 * @author v7lin E-mail:v7lin@qq.com
 */
public class SkinFilter implements FilenameFilter {

    public static final String DEFAULT_SUFFIX = ".skin";
    public static final SkinFilter DEFAULT_SKIN_FILTER = new SkinFilter(DEFAULT_SUFFIX);

    private String mSuffix;

    public SkinFilter(String suffix) {
        super();
        mSuffix = suffix;
    }

    @Override
    public boolean accept(File dir, String filename) {
        return filename.toLowerCase(Locale.getDefault()).endsWith(mSuffix);
    }
}
