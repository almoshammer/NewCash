package com.b3to.newcash.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.QueryModels.Q_CurrencyOverView;
import com.b3to.newcash.R;

import java.util.List;

public class ADP_CurrencyOverView extends RecyclerView.Adapter<ADP_CurrencyOverView.Holder> {

    List<Q_CurrencyOverView> mList;
    Context mContext;

    public ADP_CurrencyOverView(Context context) {
        mContext = context;
    }

    public void setList(List<Q_CurrencyOverView> list) {
        if (mList == null) mList = list;
        else {
            mList.clear();
            mList.addAll(list);
        }
    }

    public Q_CurrencyOverView getCurrency(int position) {
        return mList.get(position);
    }

    @NonNull
    @Override
    public ADP_CurrencyOverView.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dashboard_currency, parent, false);
        return new ADP_CurrencyOverView.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ADP_CurrencyOverView.Holder holder, int position) {
        Q_CurrencyOverView item = mList.get(position);
        holder.txtName.setText(String.format("%s (%s)", item.getName(), item.getSymbol()));

        double debit = item.getDebit() - item.getCredit();
        double credit = item.getCredit() - item.getDebit();


        if (item.getDebit() > item.getCredit()) {

            holder.txtDebit.setText(String.valueOf(debit));

            holder.imgDebit.setVisibility(View.VISIBLE);
            holder.imgCredit.setVisibility(View.INVISIBLE);
        } else if (item.getDebit() < item.getCredit()) {
            holder.txtCredit.setText(String.valueOf(credit));

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
        TextView txtName;
        TextView txtDebit, txtCredit;
        ImageView imgDebit, imgCredit;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.lbl_item_currency_name);
            txtDebit = itemView.findViewById(R.id.lbl_item_debit);
            txtCredit = itemView.findViewById(R.id.lbl_item_credit);

            imgDebit = itemView.findViewById(R.id.img_item_debit);
            imgCredit = itemView.findViewById(R.id.img_item_credit);
        }
    }
}
