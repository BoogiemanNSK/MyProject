package com.example.anonymous.myproject;


import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;

public class ProgressTextView extends android.support.v7.widget.AppCompatTextView {
    private int mMaxValue = 100;

    public ProgressTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressTextView(Context context) {
        super(context);
    }

    public void setMax(int maxValue) {
        mMaxValue = maxValue;
    }

    public int getMax() {
        return mMaxValue;
    }

    public synchronized void setProgress(String text, int value) {
        this.setText(text);

        LayerDrawable background = (LayerDrawable) this.getBackground();
        ClipDrawable barValue = (ClipDrawable) background.getDrawable(1);

        if (mMaxValue == 0)
            mMaxValue = 1;
        int newClipLevel = value * 10000 / mMaxValue;
        barValue.setLevel(newClipLevel);

        drawableStateChanged();
    }
}