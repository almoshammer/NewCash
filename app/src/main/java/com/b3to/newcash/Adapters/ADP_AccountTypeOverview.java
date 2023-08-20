package com.b3to.newcash.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.QueryModels.Q_AccountType;
import com.b3to.newcash.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ADP_AccountTypeOverview extends RecyclerView.Adapter<ADP_AccountTypeOverview.Holder> {

    List<Q_AccountType> mList;
    Context mContext;

    public ADP_AccountTypeOverview(Context context) {
        mContext = context;
    }

    public void setList(List<Q_AccountType> list) {
        if (mList == null) mList = list;
        else {
            mList.clear();
            mList.addAll(list);
        }
    }

    public Q_AccountType getType(int position) {
        return mList.get(position);
    }

    @NonNull
    @Override
    public ADP_AccountTypeOverview.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dashboard_accounttyp, parent, false);
        return new ADP_AccountTypeOverview.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ADP_AccountTypeOverview.Holder holder, int position) {
        Q_AccountType item = mList.get(position);
        holder.txtName.setText(String.valueOf(item.getName()));
        holder.txtCurrencyCredit.setText(item.getCurrency_symbol());
        holder.txtCurrencyDebit.setText(item.getCurrency_symbol());
        holder.txtCurrencyName.setText(String.valueOf(item.getCurrency_name()));

        double debit = item.getDebit(), credit = item.getCredit();

        debit = (debit > credit) ? item.getDebit() - item.getCredit() : 0;
        credit = (debit < credit) ? item.getCredit() - item.getDebit() : 0;

        debit = BigDecimal.valueOf(debit).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        credit = BigDecimal.valueOf(credit).setScale(2, RoundingMode.HALF_DOWN).doubleValue();

        if (debit > 0) {
            holder.txtDebit.setText(String.valueOf(debit));
            holder.txtCurrencyDebit.setVisibility(View.VISIBLE);
            holder.imgDebit.setVisibility(View.VISIBLE);
            holder.imgCredit.setVisibility(View.INVISIBLE);
        } else if (credit > 0) {
            holder.txtCredit.setText(String.valueOf(credit));
            holder.txtCurrencyCredit.setVisibility(View.VISIBLE);
            holder.imgDebit.setVisibility(View.INVISIBLE);
            holder.imgCredit.setVisibility(View.VISIBLE);
        } else {
            holder.imgDebit.setVisibility(View.INVISIBLE);
            holder.imgCredit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView txtName, txtCurrencyDebit, txtCurrencyCredit, txtCurrencyName;
        TextView txtDebit, txtCredit;
        ImageView imgDebit, imgCredit;


        public Holder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.lbl_item_account_type_name);
            txtCurrencyName = itemView.findViewById(R.id.lbl_item_currency_name);
            txtDebit = itemView.findViewById(R.id.lbl_item_debit);
            txtCredit = itemView.findViewById(R.id.lbl_item_credit);

            imgDebit = itemView.findViewById(R.id.img_item_debit);
            imgCredit = itemView.findViewById(R.id.img_item_credit);
            txtCurrencyDebit = itemView.findViewById(R.id.lbl_item_currency_symbol_debit);
            txtCurrencyCredit = itemView.findViewById(R.id.lbl_item_currency_symbol_credit);
        }
    }
}
