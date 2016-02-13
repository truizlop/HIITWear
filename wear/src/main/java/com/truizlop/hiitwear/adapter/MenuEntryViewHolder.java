package com.truizlop.hiitwear.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.wearable.view.WearableListView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.truizlop.hiitwear.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuEntryViewHolder extends WearableListView.ViewHolder{

    @Bind(R.id.name) TextView nameText;
    @Bind(R.id.icon) ImageView iconImage;

    public MenuEntryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void render(@StringRes int name, @DrawableRes int icon){
        nameText.setText(name);
        iconImage.setImageResource(icon);
    }
}
