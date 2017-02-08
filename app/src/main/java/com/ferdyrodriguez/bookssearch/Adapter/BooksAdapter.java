package com.ferdyrodriguez.bookssearch.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferdyrodriguez.bookssearch.Models.Item;
import com.ferdyrodriguez.bookssearch.R;
import com.ferdyrodriguez.bookssearch.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ferdyrod on 2/6/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ItemClickListener listener;
    private List<Item> data;
    private Context context;

    public BooksAdapter(Context context, List<Item> data, ItemClickListener listener){
        super();
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_layout, parent, false);
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
            holder.bind(data.get(position));
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

        public void bind(Item item) {

            String imagelUrl = item.getVolumeInfo().getImageLinks().getThumbnail();

            Picasso
                    .with(context)
                    .load(imagelUrl)
                    .fit()
                    .into(bookCover);

            bookTitle.setText(item.getVolumeInfo().getTitle());
            String authors = Utils.getAuthors(item.getVolumeInfo().getAuthors());
            bookAuthors.setText(authors);


        }
    }
}


