package com.truizlop.hiitwear.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.wearable.view.ActionPage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truizlop.hiitwear.R;
import com.truizlop.hiitwear.listener.OnVideoSelectionListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewVideoFragment extends Fragment{

    private static final String EXTRA_EXERCISE_NAME = "ViewVideoFragment.EXTRA_EXERCISE_NAME";
    private static final String EXTRA_EXERCISE_VIDEO_URL = "ViewVideoFragment.EXTRA_EXERCISE_VIDEO_URL";

    private OnVideoSelectionListener onVideoSelectionListener;

    public static ViewVideoFragment newInstance(String name, String videoURL){
        ViewVideoFragment fragment = new ViewVideoFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_EXERCISE_NAME, name);
        args.putString(EXTRA_EXERCISE_VIDEO_URL, videoURL);
        fragment.setArguments(args);

        return fragment;
    }

    @Bind(R.id.action_page) ActionPage actionPage;

    public ViewVideoFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_youtube_action, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        String name = getArguments().getString(EXTRA_EXERCISE_NAME);
        actionPage.setText(getString(R.string.view_on_youtube, name));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onVideoSelectionListener = (OnVideoSelectionListener) context;
    }

    @OnClick(R.id.action_page)
    public void onOpenVideoClicked(){
        String url = getArguments().getString(EXTRA_EXERCISE_VIDEO_URL);
        onVideoSelectionListener.onVideoURLSelected(url);
    }
}
