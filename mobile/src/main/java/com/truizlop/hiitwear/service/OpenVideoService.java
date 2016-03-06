package com.truizlop.hiitwear.service;

import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class OpenVideoService extends WearableListenerService {

    private static final String OPEN_VIDEO_PATH = "/open_video";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if(OPEN_VIDEO_PATH.equals(messageEvent.getPath())){
            String url = new String(messageEvent.getData());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
