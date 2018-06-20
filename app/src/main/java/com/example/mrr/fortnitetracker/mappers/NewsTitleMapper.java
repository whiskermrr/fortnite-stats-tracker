package com.example.mrr.fortnitetracker.mappers;

import com.example.rxjava_fortnite_api.models.blogs.Blog;
import com.example.rxjava_fortnite_api.models.blogs.BlogHolder;

import io.reactivex.Single;

public class NewsTitleMapper {

    public static Single<BlogHolder> transform(BlogHolder blogHolder) {
        for (Blog blog : blogHolder.getBlogList()) {
            String shortContent = blog.getShortContent()
                    .replace("<p>", "")
                    .replace("</p>", "");
            blog.setShortContent(shortContent);
        }
        return Single.just(blogHolder);
    }
}
