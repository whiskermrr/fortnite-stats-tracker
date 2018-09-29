package com.example.mrr.fortnitetracker.view.news;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.mrr.fortnitetracker.R;
import com.example.rxjava_fortnite_api.Utils.FortniteApiConstants;
import com.example.rxjava_fortnite_api.models.blogs.Blog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsFragment extends Fragment {

    private Blog blog;

    @BindView(R.id.news_web_view)
    WebView newsWebView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blog = (Blog) getArguments().getSerializable("blog");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        ButterKnife.bind(this, view);
        newsWebView.setWebViewClient(new CustomWebViewClient());
        populateWebView();
        return view;
    }

    private void populateWebView() {
        if(!blog.getCategory().toLowerCase().equals(FortniteApiConstants.PATCH_NOTES)) {
            String style = getActivity().getResources()
                    .getString(
                            R.string.html_web_view_style,
                            blog.getTrendingImage(),
                            blog.getTitle()
                    );
            newsWebView.loadDataWithBaseURL(
                    null,
                    style + blog.getContent(),
                    "text/html",
                    "UTF-8",
                    null);
        }
        else {
            newsWebView.loadUrl(FortniteApiConstants.PATCH_NOTE_BASE_URL + blog.getExternalLink());
        }
    }

    class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}
