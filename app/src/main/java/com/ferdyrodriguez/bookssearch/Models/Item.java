package com.ferdyrodriguez.bookssearch.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ferdyrod on 2/6/17.
 */

public class Item {

    @SerializedName("id")
    @Expose
    private String VolumeId;
    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;

    public String VolumeId() {
        return VolumeId;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
}
