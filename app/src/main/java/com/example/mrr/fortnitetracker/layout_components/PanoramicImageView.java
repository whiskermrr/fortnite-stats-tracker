package com.example.mrr.fortnitetracker.layout_components;

import android.content.Context;
import android.util.AttributeSet;

public class PanoramicImageView extends android.support.v7.widget.AppCompatImageView {
    public PanoramicImageView(Context context) {
        super(context);
    }

    public PanoramicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PanoramicImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, (int)(0.70 * widthMeasureSpec));

        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int)(0.70 * width));
    }
}
