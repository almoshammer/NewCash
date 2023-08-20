package com.b3to.newcash.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.QueryModels.Q_Account;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.Widgets;
import com.bumptech.glide.Glide;

import java.util.List;

public class ADP_Account extends RecyclerView.Adapter<ADP_Account.Holder> {

    List<Q_Account> mList;
    Context mContext;
    Widgets.IFragmentListener listener;

    public ADP_Account(Context context) {
        mContext = context;
    }

    public void setListener(Widgets.IFragmentListener listener) {
        this.listener = listener;
    }

    public void setList(List<Q_Account> list) {
        if (mList == null) mList = list;
        else {
            mList.clear();
            mList.addAll(list);
        }
    }

    public Q_Account getAccount(int position) {
        return mList.get(position);
    }

    @NonNull
    @Override
    public ADP_Account.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_account, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ADP_Account.Holder holder, int position) {
        Q_Account item = mList.get(position);
        holder.txtName.setText(item.getName());
        holder.txtDesc.setText(item.getDesc());
        holder.txtType.setText(item.getType_name());
        if (item.getImg() == null || item.getImg().length == 0)
            Widgets.setImageTextThump(holder.img, item.getName());
        else {
            Glide.with(mContext).load(item.getImg()).centerCrop().into(holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public TextView txtName, txtDesc, txtType;
        public ImageButton btnOptions;
        public ImageView img;
        LinearLayout layout;

        public Holder(@NonNull View itemView, Widgets.IFragmentListener listener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.lbl_item_account_name);
            txtDesc = itemView.findViewById(R.id.lbl_item_account_description);
            txtType = itemView.findViewById(R.id.lbl_item_account_type_name);
            btnOptions = itemView.findViewById(R.id.btn_options);
            img = itemView.findViewById(R.id.img_item_account);
            layout = itemView.findViewById(R.id.item_layout);

            btnOptions.setOnClickListener(v -> {
                listener.clickOptions(getLayoutPosition());
            });

            layout.setOnClickListener(v -> {
                listener.click(getLayoutPosition(),this);
            });
        }
    }
}
