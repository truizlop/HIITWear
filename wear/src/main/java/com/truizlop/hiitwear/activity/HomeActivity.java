package com.truizlop.hiitwear.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;

import com.truizlop.hiitwear.R;
import com.truizlop.hiitwear.adapter.MenuAdapter;

public class HomeActivity extends Activity implements WearableListView.ClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                prepareMenuList();
            }
        });
    }

    private void prepareMenuList() {
        WearableListView menuList =
                (WearableListView) findViewById(R.id.menu_list);

        menuList.setAdapter(new MenuAdapter());
        menuList.setClickListener(this);
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        int position = (int) viewHolder.itemView.getTag();

        switch (position){
            case 0:
                startHIITWithDelay();
                break;
            case 1:
                startExercisesBrowser();
                break;
            case 2:
                startCredits();
                break;
        }
    }


    @Override
    public void onTopEmptyRegionClick() {}

    private void startHIITWithDelay() {
        startActivity(DelayedStartHIITActivity.getLaunchIntent(this));
    }

    private void startExercisesBrowser(){
        startActivity(ExercisesBrowserActivity.getLaunchIntent(this));
    }

    private void startCredits(){
        startActivity(CreditsActivity.getLaunchIntent(this));
    }
}
