package com.b3to.newcash.Adapters;

import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ADP_Main_Drawer extends RecyclerView.Adapter<Holder> {
    private final List<DrawerItem> items;
    private final Map<Class<? extends DrawerItem>, Integer> viewTypes;
    private final SparseArray<DrawerItem> holderFactories;

    private OnItemSelectedListener listener;

    public ADP_Main_Drawer(List<DrawerItem> items) {
        this.items = items;
        this.viewTypes = new HashMap<>();
        this.holderFactories = new SparseArray<>();
        processViewTypes();

    }

    private void processViewTypes() {
        int type = 0;
        for (DrawerItem item : items) {
            if (!viewTypes.containsKey(item.getClass())) {
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = holderFactories.get(viewType).createViewHolder(parent);
        holder.adapter = this;

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        items.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(items.get(position).getClass());
    }

    public void setSelected(int position) {
        for (int i = 0; i < items.size(); i++) {
            DrawerItem item = items.get(i);
            if (item.isChecked()) {
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }
        if (position > -1) {
            DrawerItem newChecked = items.get(position);
            if (newChecked.isSelectable()) {
                newChecked.setChecked(true);
                notifyItemChanged(position);
            }
            if (newChecked.isSelectable() || newChecked.isCloseBtn())
                if (listener != null) listener.OnItemSelected(position);
        }
    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnItemSelectedListener {
        void OnItemSelected(int position);
    }
}


abstract class Holder extends RecyclerView.ViewHolder implements OnClickListener {

    public ADP_Main_Drawer adapter;

    public Holder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        adapter.setSelected(getAdapterPosition());
    }
}

