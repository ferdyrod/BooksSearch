package com.ferdyrodriguez.bookssearch.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ferdyrod on 2/6/17.
 */

public class BooksResponse {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }
}
