package com.truizlop.hiitwear.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;

import com.truizlop.hiitwear.R;
import com.truizlop.hiitwear.adapter.ExerciseBrowserPagerAdapter;
import com.truizlop.hiitwear.model.HIIT;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExercisesBrowserActivity extends Activity {

    public static Intent getLaunchIntent(Context context){
        return new Intent(context, ExercisesBrowserActivity.class);
    }

    @Bind(R.id.exercise_browser_pager) GridViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_browser);
        ButterKnife.bind(this);
        configureExerciseBrowserPager();
    }

    private void configureExerciseBrowserPager() {
        pager.setAdapter(new ExerciseBrowserPagerAdapter(this, getFragmentManager(), HIIT.getExercises()));
    }

}
