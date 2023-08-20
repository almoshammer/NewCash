package com.b3to.newcash.Adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.b3to.newcash.R;

public class SimpleItemDrawer extends DrawerItem<SimpleItemDrawer.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private int normalBackgroundColor;
    private int selectedBackgroundColor;

    private Drawable icon;
    private String title;

    public SimpleItemDrawer(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_drawer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);


        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);
        holder.card.setBackgroundColor(isChecked ? selectedBackgroundColor : normalBackgroundColor);
    }

    public SimpleItemDrawer withBackgroundTint(int normalBackgroundColor) {
        this.normalBackgroundColor = normalBackgroundColor;
        return this;
    }

    public SimpleItemDrawer withBackgroundSelectedTint(int selectedBackgroundColor) {
        this.selectedBackgroundColor = selectedBackgroundColor;
        return this;
    }

    public SimpleItemDrawer withSelectedIconTint(int selectedIconTint) {
        this.selectedItemIconTint = selectedIconTint;
        return this;
    }

    public SimpleItemDrawer withSelectedTextTint(int selectedItemIconTint) {
        this.selectedItemTextTint = selectedItemIconTint;
        return this;
    }

    public SimpleItemDrawer withIconTint(int normalItemIconTint) {
        this.normalItemIconTint = normalItemIconTint;
        return this;

    }

    public SimpleItemDrawer withTextTint(int normalItemTextTint) {
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }

    public static class ViewHolder extends Holder {
        private ImageView icon;
        private TextView title;
        private LinearLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.drawer_item_image);
            title = itemView.findViewById(R.id.drawer_item_text);
            card = itemView.findViewById(R.id.drawer_card);
        }
    }
}
