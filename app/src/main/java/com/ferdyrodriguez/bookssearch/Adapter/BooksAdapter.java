package com.ferdyrodriguez.bookssearch.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferdyrodriguez.bookssearch.Models.Item;
import com.ferdyrodriguez.bookssearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ferdyrod on 2/6/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{

    private ItemClickListener listener;
    private List<Item> data;
    private Context context;

    public BooksAdapter(Context context, List<Item> data, ItemClickListener listener){
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item item = data.get(position);
        String imagelUrl = item.getVolumeInfo().getImageLinks().getThumbnail();

        Picasso
                .with(context)
                .load(imagelUrl)
                .fit()
                .into(holder.bookCover);

        holder.bookTitle.setText(item.getVolumeInfo().getTitle());
        String authors = TextUtils.join(", ", item.getVolumeInfo().getAuthors());
        holder.bookAuthors.setText(authors);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public  void setBooksList(List<Item> items) {
        this.data.addAll(items);
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bookCover;
        TextView bookTitle;
        TextView bookAuthors;

        public ViewHolder(View itemView) {
            super(itemView);

            bookCover = (ImageView) itemView.findViewById(R.id.bookCover);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
            bookAuthors = (TextView) itemView.findViewById(R.id.bookAuthors);
        }
    }


}


