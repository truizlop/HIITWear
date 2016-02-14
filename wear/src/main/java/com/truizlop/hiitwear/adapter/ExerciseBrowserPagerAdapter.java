package com.truizlop.hiitwear.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import com.truizlop.hiitwear.fragment.ViewVideoFragment;
import com.truizlop.hiitwear.model.Exercise;

import java.util.List;

public class ExerciseBrowserPagerAdapter extends FragmentGridPagerAdapter{

    private Context context;
    private List<Exercise> exerciseList;

    public ExerciseBrowserPagerAdapter(Context context,FragmentManager fm, List<Exercise> exerciseList) {
        super(fm);
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @Override
    public Fragment getFragment(int row, int column) {
        Exercise exercise = exerciseList.get(row);
        if(column == 0){
            return CardFragment.create(context.getString(exercise.getName()), context.getString(exercise.getDescription()));
        }else{
            return ViewVideoFragment.newInstance(context.getString(exercise.getName()), exercise.getVideoURL());
        }
    }

    @Override
    public int getRowCount() {
        return exerciseList.size();
    }

    @Override
    public int getColumnCount(int row) {
        return 2;
    }
}
