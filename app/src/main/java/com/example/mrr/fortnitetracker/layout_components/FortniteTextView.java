package com.example.mrr.fortnitetracker.layout_components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class FortniteTextView extends android.support.v7.widget.AppCompatTextView {

    public FortniteTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FortniteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FortniteTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/BurbankBigCondensed-Bold.otf");
        setTypeface(tf ,1);
    }
}
