package com.truizlop.hiitwear.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.wearable.view.CrossfadeDrawable;
import android.support.wearable.view.GridViewPager;
import android.widget.FrameLayout;

import com.truizlop.hiitwear.R;
import com.truizlop.hiitwear.adapter.ExerciseBrowserPagerAdapter;
import com.truizlop.hiitwear.model.HIIT;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExercisesBrowserActivity extends Activity implements GridViewPager.OnPageChangeListener {

    public static Intent getLaunchIntent(Context context){
        return new Intent(context, ExercisesBrowserActivity.class);
    }

    @Bind(R.id.exercise_browser_pager) GridViewPager pager;
    @Bind(R.id.crossfade_background) FrameLayout crossfadeBackground;
    private CrossfadeDrawable crossfadeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_browser);
        ButterKnife.bind(this);
        configureCrossfade();
        configureExerciseBrowserPager();
    }

    private void configureCrossfade(){
        crossfadeDrawable = new CrossfadeDrawable();
        crossfadeDrawable.setBase(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        crossfadeDrawable.setFading(new ColorDrawable(getResources().getColor(R.color.black_transparent)));
        crossfadeBackground.setBackground(crossfadeDrawable);
    }

    private void configureExerciseBrowserPager() {
        pager.setAdapter(new ExerciseBrowserPagerAdapter(this, getFragmentManager(), HIIT.getExercises()));
        pager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int row, int column, float rowOffset, float columnOffset, int rowOffsetPixels, int columnOffsetPixels) {
        if(column == 0) {
            crossfadeDrawable.setProgress(columnOffset);
        }
    }

    @Override
    public void onPageSelected(int row, int column) {
        if(column == 0){
            crossfadeDrawable.setProgress(0);
        }else{
            crossfadeDrawable.setProgress(1);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
