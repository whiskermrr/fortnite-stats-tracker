package com.example.mrr.fortnitetracker.view.weapons;

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
import android.widget.Toast;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.models.weapons.Weapon;
import com.example.mrr.fortnitetracker.models.weapons.WeaponsHolder;
import com.example.mrr.fortnitetracker.view.MainActivity;
import com.example.mrr.fortnitetracker.view.adapters.WeaponTypeSection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

public class WeaponsFragment extends Fragment implements WeaponsContracts.View {

    public final static int SPAN_COUNT = 4;

    @Inject
    WeaponsContracts.Presenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.weapons_recycler_view)
    RecyclerView recyclerView;

    @Inject
    SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    @Inject
    PublishSubject<Weapon> clickSubject;

    private CompositeDisposable disposables;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weapons, container, false);
        ButterKnife.bind(this, view);
        disposables = new CompositeDisposable();
        presenter.getWeapons();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (sectionedRecyclerViewAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return SPAN_COUNT;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(sectionedRecyclerViewAdapter);

        disposables.add(
                clickSubject.subscribe(this::openWeaponDetailsFragment)
        );

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.dispose();
        presenter.unsubscribe();
    }

    public void openWeaponDetailsFragment(Weapon weapon) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ItemDetailsFragment.KEY_WEAPON, weapon);
        ItemDetailsFragment fragment = new ItemDetailsFragment();
        fragment.setArguments(bundle);

        ((MainActivity) getActivity()).replaceFragment(fragment);
    }

    @Override
    public void onSuccess(WeaponsHolder weaponsHolder) {
        sectionedRecyclerViewAdapter.addSection(new WeaponTypeSection(getContext(), weaponsHolder.getAssaultRifles(), clickSubject));
        sectionedRecyclerViewAdapter.addSection(new WeaponTypeSection(getContext(), weaponsHolder.getMachineGuns(), clickSubject));
        sectionedRecyclerViewAdapter.addSection(new WeaponTypeSection(getContext(), weaponsHolder.getSmgs(), clickSubject));
        sectionedRecyclerViewAdapter.addSection(new WeaponTypeSection(getContext(), weaponsHolder.getPistols(),clickSubject));
        sectionedRecyclerViewAdapter.addSection(new WeaponTypeSection(getContext(), weaponsHolder.getShotguns(), clickSubject));
        sectionedRecyclerViewAdapter.addSection(new WeaponTypeSection(getContext(), weaponsHolder.getSniperRifles(),clickSubject));
        sectionedRecyclerViewAdapter.addSection(new WeaponTypeSection(getContext(), weaponsHolder.getExplosiveWeapons(), clickSubject));
        sectionedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), "FAILED", Toast.LENGTH_SHORT).show();
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
