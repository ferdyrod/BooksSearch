package com.ferdyrodriguez.bookssearch.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ferdyrod on 2/6/17.
 */
public class ImageLinks {

    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("small")
    @Expose
    private String small;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSmall() {
        return small;
    }
}
