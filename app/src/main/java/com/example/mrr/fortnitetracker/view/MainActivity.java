package com.example.mrr.fortnitetracker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.FortniteTrackerApplication;
import com.example.mrr.fortnitetracker.Model.UserProfileModel;
import com.example.mrr.fortnitetracker.Network.FortniteApiService;
import com.example.mrr.fortnitetracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements StatsContracts.View {

    @BindView(R.id.tText)
    TextView tText;

    FortniteApiService fortniteApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fortniteApiService = FortniteTrackerApplication.get(this).getFortniteApiService();
        StatsPresenter presenter = new StatsPresenter(this, new StatsInteractor(fortniteApiService));
        presenter.getUserStats("pc", "whiskermrr");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
