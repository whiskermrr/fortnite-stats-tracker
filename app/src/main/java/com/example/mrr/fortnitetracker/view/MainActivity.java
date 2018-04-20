package com.example.mrr.fortnitetracker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.FortniteTrackerApplication;
import com.example.mrr.fortnitetracker.Model.UserProfileModel;
import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.dagger.components.DaggerMainActivityComponent;
import com.example.mrr.fortnitetracker.dagger.modules.MainActivityModule;
import com.example.mrr.fortnitetracker.view.news.NewsContracts;
import com.example.mrr.fortnitetracker.view.stats.StatsContracts;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements StatsContracts.View, NewsContracts.View {

    @BindView(R.id.tText)
    TextView tText;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    StatsContracts.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .fortniteApplicationComponent(FortniteTrackerApplication.get(this).getComponent())
                .build().inject(this);

        presenter.getUserStats("pc", "whiskermrr");
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
        tText.setText(tweets.get(0).text);
    }

    @Override
    public void onSuccess(UserProfileModel userProfile) {
        tText.setText(userProfile.getEpicUserHandle());
    }

    @Override
    public void onFailure(String message) {
        tText.setText(message);
    }
}
