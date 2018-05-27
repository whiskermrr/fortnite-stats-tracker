package com.example.mrr.fortnitetracker.view.news;

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
import com.example.mrr.fortnitetracker.view.adapters.NewsAdapter;
import com.example.rxjava_fortnite_api.Utils.FortniteApiConstants;
import com.example.rxjava_fortnite_api.models.blogs.Blog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;

public class NewsFragment extends Fragment implements NewsContracts.View {

    @Inject
    NewsContracts.Presenter presenter;

    @Inject
    NewsAdapter newsAdapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    FragmentManager fragmentManager;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.news_recycler_view)
    RecyclerView newsRecyclerView;

    private CompositeDisposable disposables;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        disposables = new CompositeDisposable();
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        presenter.getNews(null);
        newsRecyclerView.setLayoutManager(layoutManager);
        newsRecyclerView.setAdapter(newsAdapter);
        disposables.add(
                newsAdapter.clickEvent()
                        .subscribe(this::openNewsDetailsFragment)
        );
        return view;
    }

    private void openNewsDetailsFragment(Blog blog) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("blog", blog);
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        fragment.setArguments(bundle);

        if(fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(
                    fragmentManager.getBackStackEntryAt(0).getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
            );
        }

        fragmentManager.beginTransaction()
                .replace(R.id.news_frame, fragment)
                .addToBackStack(null)
                .commit();
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
    public void onSuccess(List<Blog> blogList) {
        newsAdapter.addBlogs(blogList);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
