package com.truizlop.hiitwear.adapter;

import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.truizlop.hiitwear.R;

public class MenuAdapter extends WearableListView.Adapter{

    private final int[] ENTRIES = new int[]{R.string.start_hiit, R.string.exercises, R.string.credits};
    private final int[] ICONS = new int[]{R.drawable.ic_play, R.drawable.ic_exercises, R.drawable.ic_settings};

    @Override
    public int getItemCount() {
        return ENTRIES.length;
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MenuEntryViewHolder(inflater.inflate(R.layout.view_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        ((MenuEntryViewHolder) holder).render(ENTRIES[position], ICONS[position]);
        ((MenuEntryViewHolder) holder).itemView.setTag(position);
    }
}
