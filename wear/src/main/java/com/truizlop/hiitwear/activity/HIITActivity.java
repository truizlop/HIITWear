package com.truizlop.hiitwear.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.truizlop.hiitwear.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HIITActivity extends WearableActivity {

    public static Intent getLaunchIntent(Context context){
        return new Intent(context, HIITActivity.class);
    }

    @Bind(R.id.background_image) ImageView backgroundImage;
    @Bind(R.id.round_frame) View roundFrame;
    @Bind(R.id.round_text) TextView roundText;
    @Bind(R.id.time_text) TextView timeText;
    @Bind(R.id.card_view) View cardView;
    @Bind(R.id.exercise_title_text) TextView exerciseTitleText;
    @Bind(R.id.exercise_name_text) TextView exerciseNameText;
    @Bind(R.id.icon_frame) View iconFrame;
    @Bind(R.id.icon_image) ImageView iconImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setAmbientEnabled();

        setContentView(R.layout.activity_hiit);
        ButterKnife.bind(this);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        backgroundImage.setVisibility(View.GONE);
        roundFrame.setBackground(null);
        roundText.getPaint().setAntiAlias(false);
        timeText.getPaint().setAntiAlias(false);
        cardView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        int white = getResources().getColor(R.color.white);
        exerciseTitleText.setTextColor(white);
        exerciseNameText.getPaint().setAntiAlias(false);
        exerciseNameText.setTextColor(white);
        exerciseNameText.getPaint().setAntiAlias(false);
        iconFrame.setVisibility(View.GONE);
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        backgroundImage.setVisibility(View.VISIBLE);
        roundFrame.setBackgroundResource(R.drawable.white_round_rect);
        roundText.getPaint().setAntiAlias(true);
        timeText.getPaint().setAntiAlias(true);
        cardView.setBackgroundColor(getResources().getColor(R.color.white));
        exerciseTitleText.setTextColor(getResources().getColor(R.color.secondary_color));
        exerciseTitleText.getPaint().setAntiAlias(true);
        exerciseNameText.setTextColor(getResources().getColor(R.color.text_color));
        exerciseNameText.getPaint().setAntiAlias(true);
        iconFrame.setVisibility(View.VISIBLE);
    }

}
