package com.ferdyrodriguez.bookssearch.Services;

import com.ferdyrodriguez.bookssearch.Models.BooksResponse;
import com.ferdyrodriguez.bookssearch.Models.VolumeInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ferdyrod on 2/6/17.
 */

public interface GoogleBooksService {

    @GET("books/v1/volumes")
    Call<BooksResponse> getBooksInfo(@Query("q") String query);

    @GET("books/v1/volumes/{volumeId}")
    Call<VolumeInfo> getVolumeInfo(@Path("volumeId") String volumeId);
}
