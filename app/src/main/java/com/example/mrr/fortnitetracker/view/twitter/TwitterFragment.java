package com.example.mrr.fortnitetracker.view.twitter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.dagger.modules.TwitterFragmentModule;
import com.example.mrr.fortnitetracker.view.adapters.CustomTwitterAdapter;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;


public class TwitterFragment extends Fragment implements TwitterContracts.View {

    @Inject
    TwitterContracts.Presenter presenter;

    @Inject
    @Named(TwitterFragmentModule.TWITTER_FRAGMENT_MANAGER)
    FragmentManager fragmentManager;

    @Inject
    CustomTwitterAdapter tweetsAdapter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.twitter_recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter, container, false);
        ButterKnife.bind(this, view);
        linearLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(tweetsAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(linearLayout.findLastCompletelyVisibleItemPosition() == tweetsAdapter.getItemCount() - 1) {
                    presenter.getTweets();
                }
            }
        });
        presenter.getTweets();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<Tweet> tweets) {
        tweetsAdapter.addOlderTweets(tweets);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
