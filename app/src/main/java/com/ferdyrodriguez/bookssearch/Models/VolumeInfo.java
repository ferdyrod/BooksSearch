package com.ferdyrodriguez.bookssearch.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ferdyrod on 2/6/17.
 */

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("authors")
    @Expose
    private List<String> authors;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }
}
