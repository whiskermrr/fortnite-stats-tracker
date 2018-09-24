package com.example.mrr.fortnitetracker.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.R;
import com.example.rxjava_fortnite_api.models.blogs.Blog;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private LinkedList<Blog> blogs;
    private PublishSubject<Blog> clickSubject;

    public NewsAdapter(Context context) {
        this.context = context;
        blogs = new LinkedList<>();
        inflater = LayoutInflater.from(context);
        clickSubject = PublishSubject.create();
    }

    public void addBlogs(List<Blog> newBlogs) {
        for(Blog blog : newBlogs) {
            blogs.add(blog);
            notifyItemInserted(blogs.size() - 1);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Picasso.with(context).load(blogs.get(position).getTrendingImage()).into(holder.newsImage);
        holder.tTitle.setText(blogs.get(position).getTitle());
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        String date = null;
        try {
            date = formatter.format(parser.parse(blogs.get(position).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tDate.setText(date);
        holder.tShortContent.setText(blogs.get(0).getShortContent().trim());
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }


    public Observable<Blog> clickEvent() {
        return clickSubject;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImage;
        TextView tTitle;
        TextView tDate;
        TextView tShortContent;

        NewsViewHolder(View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.news_image_view);
            tTitle = itemView.findViewById(R.id.news_title);
            tDate = itemView.findViewById(R.id.news_date);
            tShortContent = itemView.findViewById(R.id.news_short_content);

            itemView.setOnClickListener(view -> clickSubject.onNext(blogs.get(getAdapterPosition())));
        }
    }
}
