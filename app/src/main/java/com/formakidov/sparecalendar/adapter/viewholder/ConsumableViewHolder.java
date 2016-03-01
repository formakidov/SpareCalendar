package com.formakidov.sparecalendar.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.model.BaseModel;
import com.formakidov.sparecalendar.model.Consumable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConsumableViewHolder extends BaseViewHolder {
    @Bind(R.id.consumable_name) TextView name;
    @Bind(R.id.consumable_comment) TextView comment;
    @Bind(R.id.consumable_km_left) TextView kmLeft;

    public ConsumableViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(BaseModel model) {
        Consumable consumable = (Consumable) model;
        name.setText(consumable.getName());
        comment.setText(consumable.getComment());
        kmLeft.setText(String.valueOf(consumable.getKmLeft()));
    }
}
