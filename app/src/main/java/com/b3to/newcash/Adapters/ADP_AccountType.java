package com.b3to.newcash.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Dialogs.DG_BottomModelOptions;
import com.b3to.newcash.Models.AccountType;
import com.b3to.newcash.Models.Currency;
import com.b3to.newcash.R;

import java.util.List;

public class ADP_AccountType extends RecyclerView.Adapter<ADP_AccountType.Holder> {

    List<AccountType> mList;
    DG_BottomModelOptions.IOptionClick listener;
    Context mContext;

    public ADP_AccountType(Context context) {
        mContext = context;
    }

    public void setOnOptionsClick(DG_BottomModelOptions.IOptionClick listener) {
        this.listener = listener;
    }

    public void setList(List<AccountType> list) {
        if (mList == null) mList = list;
        else {
            mList.clear();
            mList.addAll(list);
        }
    }

    public AccountType getAccountType(int position) {
        return mList.get(position);
    }

    @NonNull
    @Override
    public ADP_AccountType.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_accounttype, parent, false);
        return new ADP_AccountType.Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ADP_AccountType.Holder holder, int position) {
        AccountType item = mList.get(position);
        holder.txtName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageButton btnOptions;

        public Holder(@NonNull View itemView, DG_BottomModelOptions.IOptionClick listener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.lbl_item_account_type_name);
            btnOptions = itemView.findViewById(R.id.btn_options);
            btnOptions.setOnClickListener(v -> {
                listener.options(getLayoutPosition());
            });
        }
    }
}
