package com.example.mrr.fortnitetracker.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetBuilder;
import com.twitter.sdk.android.tweetui.CompactTweetView;

import java.util.LinkedList;
import java.util.List;

public class CustomTwitterAdapter extends RecyclerView.Adapter<CustomTwitterAdapter.TweetViewHolder> {

    private Context context;
    private LinkedList<Tweet> tweets;
    private int styleResourceId;

    public CustomTwitterAdapter(Context context, int styleResourceId) {
        this.context = context;
        this.tweets = new LinkedList<>();
        this.styleResourceId = styleResourceId;
    }

    public CustomTwitterAdapter(Context context, List<Tweet> tweets, int styleResourceId) {
        this.context = context;
        this.tweets = new LinkedList<>(tweets);
        this.styleResourceId = styleResourceId;
    }

    public void addOlderTweets(List<Tweet> newTweets) {
        for(Tweet tweet : newTweets) {
            tweets.add(tweet);
            notifyItemInserted(tweets.size() - 1);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tweet tweet = new TweetBuilder().build();
        CompactTweetView compactTweetView = new CompactTweetView(context, tweet, styleResourceId);
        return new TweetViewHolder(compactTweetView);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder, int position) {
        final Tweet tweet = tweets.get(position);
        CompactTweetView compactTweetView = (CompactTweetView) holder.itemView;
        compactTweetView.setTweet(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }


    class TweetViewHolder extends RecyclerView.ViewHolder {

        TweetViewHolder(CompactTweetView itemView) {
            super(itemView);
        }
    }
}
