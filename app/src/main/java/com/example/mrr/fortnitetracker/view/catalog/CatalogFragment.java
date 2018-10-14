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
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class CatalogFragment extends Fragment implements CatalogContracts.View {

    @Inject
    CatalogContracts.Presenter catalogPresenter;

    @Inject
    AssetManager assetManager;

    @BindView(R.id.rvWeeklyStorefront)
    RecyclerView rvWeeklyStorefront;

    @BindView(R.id.tvFeaturedCycle)
    TextView tvFeaturedCycle;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    private StorefrontAdapter weeklyStorefrontAdapter;

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

        GridLayoutManager layoutManager = new GridLayoutManager(
                getContext(), 2);

        weeklyStorefrontAdapter = new StorefrontAdapter(getContext());
        rvWeeklyStorefront.setLayoutManager(layoutManager);
        rvWeeklyStorefront.setAdapter(weeklyStorefrontAdapter);

        tvFeaturedCycle.setOnClickListener(event ->
            catalogPresenter.cycleWeeklyOffer()
        );

        catalogPresenter.getCurrentShop();

        return view;
    }

    @Override
    public void initWeeklyStorefront(List<CatalogEntryViewModel> entries) {
        weeklyStorefrontAdapter.setCatalogEntries(entries);
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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
