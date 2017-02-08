package com.ferdyrodriguez.bookssearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferdyrodriguez.bookssearch.Models.Item;
import com.ferdyrodriguez.bookssearch.Models.VolumeInfo;
import com.ferdyrodriguez.bookssearch.Services.GoogleBooksService;
import com.ferdyrodriguez.bookssearch.Services.RestClient;
import com.ferdyrodriguez.bookssearch.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.function.ToLongBiFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthors;
    private TextView bookDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        String volumeId = extras.getString(Utils.VOLUME_ID);
        loadJson(volumeId);
    }

    private void loadJson(final String volumeId) {

        GoogleBooksService service = RestClient.createService(GoogleBooksService.class);
        Call<Item> call = service.getVolumeInfo(volumeId);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()) {
                    Item item = response.body();
                    Log.d(TAG, "onResponse: " + item.getVolumeInfo().getTitle());
                    setupViews(item);
                } else {
                    try {
                        Log.e(TAG, "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    private void setupViews(Item item) {

        String imageUrl = item.getVolumeInfo().getImageLinks().getThumbnail();
        String title = item.getVolumeInfo().getTitle();
        String authors = Utils.getAuthors(item.getVolumeInfo().getAuthors());
        String description = item.getVolumeInfo().getDescription();

        bookCover = (ImageView) findViewById(R.id.bookDetailCover);
        bookTitle = (TextView) findViewById(R.id.bookDetailTitle);
        bookAuthors = (TextView) findViewById(R.id.bookDetailAuthors);
        bookDescription = (TextView) findViewById(R.id.bookDetailDescription);

        Picasso
                .with(this)
                .load(imageUrl)
                .into(bookCover);
        if(!TextUtils.isEmpty(title)) {
            bookTitle.setText(title);
        } else {
            bookTitle.setText(R.string.title_error);
        }

        bookAuthors.setText(authors);
        if(!TextUtils.isEmpty(description)) {
            String formattedDesc = description.replace("<br>", "\n\n");
            bookDescription.setText(formattedDesc);
        } else {
            bookDescription.setText(R.string.description_not_found);
        }
    }
}
