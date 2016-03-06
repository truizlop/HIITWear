package com.truizlop.hiitwear.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.CrossfadeDrawable;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.truizlop.hiitwear.R;
import com.truizlop.hiitwear.adapter.ExerciseBrowserPagerAdapter;
import com.truizlop.hiitwear.listener.OnVideoSelectionListener;
import com.truizlop.hiitwear.model.HIIT;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExercisesBrowserActivity extends Activity implements GridViewPager.OnPageChangeListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        OnVideoSelectionListener {

    private static final String OPEN_VIDEO_PATH = "/open_video";

    public static Intent getLaunchIntent(Context context){
        return new Intent(context, ExercisesBrowserActivity.class);
    }

    @Bind(R.id.exercise_browser_pager) GridViewPager pager;
    @Bind(R.id.crossfade_background) FrameLayout crossfadeBackground;
    @Bind(R.id.pager_indicator) DotsPageIndicator pagerIndicator;
    private CrossfadeDrawable crossfadeDrawable;

    private Node node;
    private GoogleApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_browser);
        ButterKnife.bind(this);
        connectApiClient();
        configureCrossfade();
        configureExerciseBrowserPager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        apiClient.connect();
    }

    private void connectApiClient(){
        apiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void configureCrossfade(){
        crossfadeDrawable = new CrossfadeDrawable();
        crossfadeDrawable.setBase(new ColorDrawable(ContextCompat.getColor(this, android.R.color.transparent)));
        crossfadeDrawable.setFading(new ColorDrawable(ContextCompat.getColor(this, R.color.black_transparent)));
        crossfadeBackground.setBackground(crossfadeDrawable);
    }

    private void configureExerciseBrowserPager() {
        pager.setAdapter(new ExerciseBrowserPagerAdapter(this, getFragmentManager(), HIIT.getExercises()));
        pagerIndicator.setPager(pager);
        pagerIndicator.setOnPageChangeListener(this);
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
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        resolveNode();
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    @Override
    public void onVideoURLSelected(String url) {
        sendVideoURL(url);
    }

    private void resolveNode() {

        Wearable.NodeApi.getConnectedNodes(apiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(@NonNull NodeApi.GetConnectedNodesResult nodesResult) {
                for (Node node : nodesResult.getNodes()) {
                    if (node.isNearby()) {
                        ExercisesBrowserActivity.this.node = node;
                    }
                }
            }
        });
    }

    private void sendVideoURL(String videoURL) {
        if (node != null && apiClient!=null && apiClient.isConnected()) {
            Wearable.MessageApi.sendMessage(
                    apiClient, node.getId(), OPEN_VIDEO_PATH, videoURL.getBytes()).setResultCallback(
                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(@NonNull MessageApi.SendMessageResult sendMessageResult) {
                            if (sendMessageResult.getStatus().isSuccess()) {
                                showConfirmation();
                            }else{
                                showErrorOpeningVideo();
                            }
                        }
                    }
            );
        }else{
            showErrorOpeningVideo();
        }
    }

    private void showConfirmation(){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.OPEN_ON_PHONE_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                getString(R.string.common_open_on_phone));
        startActivity(intent);
    }

    private void showErrorOpeningVideo(){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.FAILURE_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                getString(R.string.could_not_open_video));
        startActivity(intent);
    }
}
