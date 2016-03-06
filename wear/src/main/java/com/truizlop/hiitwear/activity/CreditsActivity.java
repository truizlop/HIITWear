package com.truizlop.hiitwear.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;

import com.truizlop.hiitwear.R;

public class CreditsActivity extends Activity {

    public static Intent getLaunchIntent(Context context){
        return new Intent(context, CreditsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CardFragment cardFragment = CardFragment.create(getString(R.string.app_name),
                getString(R.string.created_by));
        fragmentTransaction.add(R.id.card_container, cardFragment);
        fragmentTransaction.commit();
    }

}
