package com.ferdyrodriguez.bookssearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ferdyrodriguez.bookssearch.Adapter.BooksAdapter;
import com.ferdyrodriguez.bookssearch.Adapter.ItemClickListener;
import com.ferdyrodriguez.bookssearch.Models.BooksResponse;
import com.ferdyrodriguez.bookssearch.Models.Item;
import com.ferdyrodriguez.bookssearch.Services.GoogleBooksService;
import com.ferdyrodriguez.bookssearch.Services.RestClient;
import com.ferdyrodriguez.bookssearch.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<Item> booksData = new ArrayList<>();
    private BooksAdapter booksAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViews();

    }

    private void setupViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        booksAdapter = new BooksAdapter(this,
                booksData,
                new ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String bookId = booksData.get(position).VolumeId();
                        Log.d(TAG, "onItemClick: bookId " + bookId);
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra(Utils.VOLUME_ID, bookId);
                        startActivity(intent);
                    }
                });
        recyclerView.setAdapter(booksAdapter);

        loadJson();
    }

    private void loadJson() {

        GoogleBooksService service = RestClient.createService(GoogleBooksService.class);
        Call<BooksResponse> call = service.getBooksInfo("eventos", 40);
        call.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                if(response.isSuccessful()){
                    BooksResponse booksResponse = response.body();
                    Log.d(TAG, "onResponse: response " + response.body().getItems().get(0).getVolumeInfo().getTitle());
                    booksData = booksResponse.getItems();
                    booksAdapter.setBooksList(booksData);
                } else {
                    try {
                        Log.e(TAG, "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        });

    }

}
