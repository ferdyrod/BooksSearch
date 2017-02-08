package com.ferdyrodriguez.bookssearch.Utils;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by ferdyrod on 2/7/17.
 */
public class Utils {

    public static final String VOLUME_ID = "volumeId";

    public static String getAuthors(List<String> authors) {

        return authors == null ? "No authors found" : TextUtils.join(", ", authors);

    }
}
