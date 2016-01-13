package com.v7lin.android.env.extra;

import com.v7lin.android.env.font.FontFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

public class FontFilter implements FilenameFilter {
    public FontFilter() {
        super();
    }

    @Override
    public boolean accept(File dir, String filename) {
        return filename.toLowerCase(Locale.getDefault()).endsWith(FontFactory.TTF_ENDING) ||
                filename.toLowerCase(Locale.getDefault()).endsWith(FontFactory.OTF_ENDING);
    }
}
