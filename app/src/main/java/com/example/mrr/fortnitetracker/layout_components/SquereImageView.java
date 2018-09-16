package com.example.mrr.fortnitetracker.layout_components;

import android.content.Context;
import android.util.AttributeSet;

public class SquereImageView extends android.support.v7.widget.AppCompatImageView {
    public SquereImageView(Context context) {
        super(context);
    }

    public SquereImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquereImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
