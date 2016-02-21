package com.truizlop.hiitwear.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.truizlop.hiitwear.R;

public class HIITActivity extends Activity {

    public static Intent getLaunchIntent(Context context){
        return new Intent(context, HIITActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiit);
    }
}
