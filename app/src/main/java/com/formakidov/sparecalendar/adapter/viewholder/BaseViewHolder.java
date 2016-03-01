package com.formakidov.sparecalendar.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.formakidov.sparecalendar.model.BaseModel;

import butterknife.ButterKnife;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(BaseModel model);
}
