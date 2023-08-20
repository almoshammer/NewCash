package com.b3to.newcash.Adapters;

import android.view.ViewGroup;

public abstract class DrawerItem<T extends Holder> {
    protected boolean isChecked;
    private boolean isSelectable = true;
    private boolean isCloseBtn;

    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

    public DrawerItem<T> setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public DrawerItem<T> setIsCloseBtn(boolean isCloseBtn) {
        this.isCloseBtn = isCloseBtn;
        this.isSelectable = false;
        return this;
    }

    public boolean isCloseBtn() {
        return this.isCloseBtn;

    }
}
