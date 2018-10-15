package com.example.mrr.fortnitetracker.view.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.dagger.modules.NewsFragmentModule;
import com.example.mrr.fortnitetracker.view.MainActivity;
import com.example.mrr.fortnitetracker.view.adapters.NewsAdapter;
import com.example.rxjava_fortnite_api.models.blogs.Blog;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;


public class NewsFragment extends Fragment implements NewsContracts.View {

    @Inject
    NewsContracts.Presenter presenter;

    @Inject
    NewsAdapter newsAdapter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.news_recycler_view)
    RecyclerView newsRecyclerView;

    private CompositeDisposable disposables;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        disposables = new CompositeDisposable();
        layoutManager = new LinearLayoutManager(getActivity());
        newsRecyclerView.setLayoutManager(layoutManager);
        newsRecyclerView.setAdapter(newsAdapter);
        newsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(layoutManager.findLastCompletelyVisibleItemPosition() == newsAdapter.getItemCount() - 1) {
                    presenter.getNews(null);
                }
            }
        });
        presenter.getNews(null);
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

        ((MainActivity) getActivity()).replaceFragment(fragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.dispose();
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
