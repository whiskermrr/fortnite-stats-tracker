package com.example.mrr.fortnitetracker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.FortniteTrackerApplication;
import com.example.mrr.fortnitetracker.Model.UserProfileModel;
import com.example.mrr.fortnitetracker.Network.RxTwitterApiClient;
import com.example.mrr.fortnitetracker.Network.RxTwitterApiService;
import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.dagger.components.DaggerMainActivityComponent;
import com.example.mrr.fortnitetracker.dagger.modules.MainActivityModule;
import com.example.mrr.fortnitetracker.view.stats.StatsContracts;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements StatsContracts.View {

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

        //presenter.getUserStats("pc", "whiskermrr");

        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        RxTwitterApiClient apiClient = new RxTwitterApiClient();
        TwitterCore.getInstance().addGuestApiClient(apiClient);
        RxTwitterApiService apiService = apiClient.getRxService();
        apiService.getNews("fortnitegame").enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                Toast.makeText(MainActivity.this,
                        result.data.get(0).text
                        , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this, "FAILed", Toast.LENGTH_SHORT).show();
            }
        });

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
    public void onSuccess(UserProfileModel userProfile) {
        tText.setText(userProfile.getEpicUserHandle());
    }

    @Override
    public void onFailure(String message) {
        tText.setText(message);
    }
}
