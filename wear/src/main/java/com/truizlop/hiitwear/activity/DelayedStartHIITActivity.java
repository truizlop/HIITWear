package com.truizlop.hiitwear.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;

import com.truizlop.hiitwear.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DelayedStartHIITActivity extends Activity implements DelayedConfirmationView.DelayedConfirmationListener{

    private static final long TEN_SECONDS = 10000;

    public static Intent getLaunchIntent(Context context){
        return new Intent(context, DelayedStartHIITActivity.class);
    }

    @Bind(R.id.delayed_confirmation) DelayedConfirmationView delayedConfirmationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_start_hiit);
        ButterKnife.bind(this);
        configureDelayedStart();
    }

    private void configureDelayedStart() {
        delayedConfirmationView.setTotalTimeMs(TEN_SECONDS);
        delayedConfirmationView.setListener(this);
        delayedConfirmationView.start();
    }

    @Override
    public void onTimerFinished(View view) {
        finish();
    }

    @Override
    public void onTimerSelected(View view) {
        finish();
    }
}
