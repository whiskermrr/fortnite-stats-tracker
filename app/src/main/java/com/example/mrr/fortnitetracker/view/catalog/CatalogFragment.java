package com.example.mrr.fortnitetracker.view.catalog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.models.catalog.CatalogEntryViewModel;
import com.example.mrr.fortnitetracker.models.catalog.CatalogViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class CatalogFragment extends Fragment implements CatalogContracts.View {

    @Inject
    CatalogContracts.Presenter catalogPresenter;

    @BindView(R.id.tvTest)
    TextView tvTest;

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

        catalogPresenter.getCurrentShop();

        return view;
    }

    @Override
    public void onSuccess(CatalogViewModel catalog) {
        StringBuilder stringBuilder = new StringBuilder();
        for(CatalogEntryViewModel entry : catalog.getDailyOffer()) {
            stringBuilder
                    .append(entry.getName())
                    .append(" ")
                    .append(entry.getDisplayAssetPath())
                    .append(" ")
                    .append(entry.getEntryType())
                    .append(" ")
                    .append(entry.getPrice())
                    .append("\n");
        }

        tvTest.setText(stringBuilder.toString());
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
