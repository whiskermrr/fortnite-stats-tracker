package com.example.mrr.fortnitetracker.view.catalog;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;
import com.example.mrr.fortnitetracker.models.catalog.CatalogViewModel;
import com.example.mrr.fortnitetracker.view.adapters.StorefrontAdapter;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class CatalogFragment extends Fragment implements CatalogContracts.View {

    @Inject
    CatalogContracts.Presenter catalogPresenter;

    @Inject
    AssetManager assetManager;

    @BindView(R.id.rvWeeklyStorefront)
    RecyclerView rvWeeklyStorefront;

    @BindView(R.id.rvDailyStorefront)
    RecyclerView rvDailyStorefront;

    @BindView(R.id.tvFeaturedCycle)
    TextView tvFeaturedCycle;

    @BindView(R.id.tvDailyCycle)
    TextView tvDailyCycle;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindViews({
            R.id.tvFeaturedCycle,
            R.id.tvFeaturedShop,
            R.id.layoutDiagonal,
            R.id.layoutDiagonal2,
            R.id.tvDailyShop,
            R.id.tvDailyCycle,
            R.id.layoutDiagonal3,
            R.id.layoutDiagonal4
    })
    List<View> featuredItemsTitle;


    private StorefrontAdapter weeklyStorefrontAdapter;
    private StorefrontAdapter dailyStorefrontAdapter;

    public static final ButterKnife.Setter<View, Integer> VISIBILITY = (view, value, index) -> view.setVisibility(value);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        ButterKnife.bind(this, view);


        GridLayoutManager layoutWeeklyManager = new GridLayoutManager(
                getContext(), 2);

        GridLayoutManager layoutDailyManager = new GridLayoutManager(
                getContext(), 2);

        weeklyStorefrontAdapter = new StorefrontAdapter(getContext(), true);
        dailyStorefrontAdapter = new StorefrontAdapter(getContext(), false);

        rvWeeklyStorefront.setLayoutManager(layoutWeeklyManager);
        rvWeeklyStorefront.setAdapter(weeklyStorefrontAdapter);

        rvDailyStorefront.setLayoutManager(layoutDailyManager);
        rvDailyStorefront.setAdapter(dailyStorefrontAdapter);

        tvFeaturedCycle.setOnClickListener(event ->
            catalogPresenter.cycleWeeklyOffer()
        );

        tvDailyCycle.setOnClickListener(event ->
            catalogPresenter.cycleDailyOffer()
        );

        catalogPresenter.getCurrentShop();

        return view;
    }

    @Override
    public void initWeeklyStorefront(List<CatalogEntryViewModel> entries) {
        weeklyStorefrontAdapter.setCatalogEntries(entries);
    }

    @Override
    public void initDailyStorefront(List<CatalogEntryViewModel> entries) {
        dailyStorefrontAdapter.setCatalogEntries(entries);
    }

    @Override
    public void updateTime(String time) {
        Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        catalogPresenter.unsubscribe();
    }

    @Override
    public void onSuccess(CatalogViewModel catalog) {
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ButterKnife.apply(featuredItemsTitle, VISIBILITY, View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ButterKnife.apply(featuredItemsTitle, VISIBILITY, View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
