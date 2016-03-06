package com.truizlop.hiitwear.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truizlop.hiitwear.R;

public class WearableListItemLayout extends LinearLayout
             implements WearableListView.OnCenterProximityListener {

    private ImageView circle;
    private TextView name;

    private final float fadedTextAlpha;
    private final int fadedCircleColor;
    private final int chosenCircleColor;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);

        fadedTextAlpha = getResources()
                            .getInteger(R.integer.action_text_faded_alpha) / 100f;
        fadedCircleColor = ContextCompat.getColor(getContext(), R.color.secondary_color);
        chosenCircleColor = ContextCompat.getColor(getContext(), R.color.primary_color);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        circle = (ImageView) findViewById(R.id.circle);
        name = (TextView) findViewById(R.id.name);
    }

    @Override
    public void onCenterPosition(boolean animate) {
        name.setAlpha(1f);
        ((GradientDrawable) circle.getDrawable()).setColor(chosenCircleColor);
        circle.setScaleX(1);
        circle.setScaleY(1);
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        ((GradientDrawable) circle.getDrawable()).setColor(fadedCircleColor);
        name.setAlpha(fadedTextAlpha);
        circle.setScaleX(.8f);
        circle.setScaleY(.8f);
    }
}