package com.truizlop.hiitwear.model;

import android.support.annotation.StringRes;

public class Exercise {
    private @StringRes int name;
    private @StringRes int description;
    private String videoURL;

    public Exercise(@StringRes int name, @StringRes int description, String videoURL) {
        this.description = description;
        this.name = name;
        this.videoURL = videoURL;
    }

    public int getDescription() {
        return description;
    }

    public int getName() {
        return name;
    }

    public String getVideoURL() {
        return videoURL;
    }
}
