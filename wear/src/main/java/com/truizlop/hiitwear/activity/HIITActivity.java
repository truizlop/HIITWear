package com.truizlop.hiitwear.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.truizlop.hiitwear.R;
import com.truizlop.hiitwear.model.Exercise;
import com.truizlop.hiitwear.model.HIIT;

import java.util.List;

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
    @Bind(R.id.dismiss_overlay) DismissOverlayView dismissOverlay;

    private List<Exercise> exercises;
    private int currentExercise;
    private int timeRemaining;
    private boolean isRest;
    private Handler handler;
    private GestureDetector gestureDetector;
    private Vibrator vibrator;

    private Runnable exerciseTimeUpdate = new Runnable() {
        @Override
        public void run() {
            timeRemaining--;

            if(timeRemaining < 0){
                if (currentExercise == exercises.size() - 1) {
                    finish();
                } else {
                    timeRemaining = 10;
                    isRest = true;
                    showRest();
                    handler.postDelayed(restTimeUpdate, 1000);
                }
            }else {
                vibrateIfNecessary();
                showTimeRemaining();
                handler.postDelayed(this, 1000);
            }
        }
    };

    private Runnable restTimeUpdate = new Runnable() {
        @Override
        public void run() {
            timeRemaining--;

            if(timeRemaining < 0){
                currentExercise++;
                timeRemaining = 20;
                isRest = false;
                showCurrentExercise();
                handler.postDelayed(exerciseTimeUpdate, 1000);
            }else{
                vibrateIfNecessary();
                showTimeRemaining();
                handler.postDelayed(this, 1000);
            }
        }
    };

    private void vibrateIfNecessary() {
        if(timeRemaining == 0){
            vibrateLong();
        }else if(timeRemaining < 3){
            vibrateShort();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setAmbientEnabled();

        setContentView(R.layout.activity_hiit);
        ButterKnife.bind(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        dismissOverlay.setIntroText(R.string.long_press_intro);
        dismissOverlay.showIntroIfNecessary();

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                dismissOverlay.show();
            }
        });

        currentExercise = 0;
        timeRemaining = 20;
        isRest = false;
        exercises = HIIT.getExercises();
        handler = new Handler();
        showCurrentExercise();
        showTimeRemaining();
        handler.postDelayed(exerciseTimeUpdate, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    private void showCurrentExercise(){
        Exercise exercise = exercises.get(currentExercise);
        exerciseNameText.setText(exercise.getName());
        exerciseTitleText.setText(R.string.current_exercise);
        roundText.setText(getString(R.string.round, currentExercise + 1));
        iconImage.setImageResource(R.drawable.ic_exercises);
        if(!isAmbient()){
            iconFrame.setBackgroundResource(R.drawable.red_circle);
        }
    }

    private void showRest(){
        Exercise exercise = exercises.get(currentExercise + 1);
        exerciseNameText.setText(exercise.getName());
        exerciseTitleText.setText(R.string.next_exercise);
        roundText.setText(R.string.rest);
        iconImage.setImageResource(R.drawable.ic_rest);
        if(!isAmbient()) {
            iconFrame.setBackgroundResource(R.drawable.green_circle);
        }
    }

    private void showTimeRemaining() {
        timeText.setText(String.format("%d", timeRemaining));
    }

    private void vibrateShort(){
        long[] vibrationPattern = {0, 50};
        //-1 - don't repeat
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
    }

    private void vibrateLong(){
        long[] vibrationPattern = {0, 500};
        //-1 - don't repeat
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        backgroundImage.setVisibility(View.GONE);
        roundFrame.setBackground(null);
        roundText.getPaint().setAntiAlias(false);
        timeText.getPaint().setAntiAlias(false);
        cardView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        int white = ContextCompat.getColor(this, R.color.white);
        exerciseTitleText.setTextColor(white);
        exerciseNameText.getPaint().setAntiAlias(false);
        exerciseNameText.setTextColor(white);
        exerciseNameText.getPaint().setAntiAlias(false);
        iconFrame.setBackground(null);
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        backgroundImage.setVisibility(View.VISIBLE);
        roundFrame.setBackgroundResource(R.drawable.white_round_rect);
        roundText.getPaint().setAntiAlias(true);
        timeText.getPaint().setAntiAlias(true);
        cardView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        exerciseTitleText.setTextColor(ContextCompat.getColor(this,  R.color.secondary_color));
        exerciseTitleText.getPaint().setAntiAlias(true);
        exerciseNameText.setTextColor(ContextCompat.getColor(this, R.color.text_color));
        exerciseNameText.getPaint().setAntiAlias(true);
        if(isRest){
            iconFrame.setBackgroundResource(R.drawable.green_circle);
        } else {
            iconFrame.setBackgroundResource(R.drawable.red_circle);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
