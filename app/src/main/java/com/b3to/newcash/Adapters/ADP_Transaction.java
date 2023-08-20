package com.b3to.newcash.Adapters;

import android.annotation.SuppressLint;
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

import com.animsh.animatedcheckbox.AnimatedCheckBox;
import com.b3to.newcash.QueryModels.Q_Transaction;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.DML;
import com.b3to.newcash.Tools.Widgets;

import java.text.DateFormat;
import java.util.List;

public class ADP_Transaction extends RecyclerView.Adapter<ADP_Transaction.Holder> {

    private List<Q_Transaction> mList;
    private Widgets.IFragmentListener listener;
    private final Context mContext;
    private boolean account_name_visible = true;

    public void setAccount_name_visible(boolean account_name_visible) {
        this.account_name_visible = account_name_visible;
    }

    public void setListener(Widgets.IFragmentListener listener) {
        this.listener = listener;
    }

    public ADP_Transaction(Context context) {
        mContext = context;
    }

    public void setList(List<Q_Transaction> list) {
        if (mList == null) mList = list;
        else {
            mList.clear();
            mList.addAll(list);
        }
    }

    public Q_Transaction getTransaction(int position) {
        return mList.get(position);
    }

    @NonNull
    @Override
    public ADP_Transaction.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_transaction, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ADP_Transaction.Holder holder, int position) {
        Q_Transaction item = mList.get(position);
        if (item.getType() == 0) //debit
        {
            holder.imgType.setImageDrawable(mContext.getDrawable(R.drawable.ic_arrow_down));
            // holder.imgType.setColorFilter(R.color.colorTertiary_light);

        } else if (item.getType() == 1) //credit
        {
            holder.imgType.setImageDrawable(mContext.getDrawable(R.drawable.ic_arrow_up));
            // holder.imgType.setColorFilter(R.color.primary_light);
        }
        if (!account_name_visible)
            holder.txtAccountName.setVisibility(View.GONE);
        else holder.txtAccountName.setText(item.getAccount_name());
        // begin set date
        holder.txtDate.setText(DateFormat.getDateInstance().format(item.getDate()));
        holder.txtFormattedDate.setText(DML.getRelatedDate(item.getDate(), mContext));
        // End set date
        holder.txtDesc.setText(item.getDesc());
        holder.txtCurrencySymbol.setText(item.getCurrency_symbol());
        holder.txtAmount.setText(String.valueOf(item.getAmount()));

        holder.txtTransType.setText(mContext.getResources().getString(item.getType() == 0 ? R.string.debit : R.string.credit));
        if (listener != null && listener.isSelectable()) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
            holder.btnOptions.setVisibility(View.GONE);
        }
        if (listener == null) holder.btnOptions.setVisibility(View.GONE);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView imgType;
        TextView txtAccountName, txtDate, txtFormattedDate, txtDesc, txtCurrencySymbol, txtAmount, txtTransType;
        AnimatedCheckBox checkBox;
        ImageButton btnOptions;
        LinearLayout layout;

        public Holder(@NonNull View itemView, Widgets.IFragmentListener listener) {
            super(itemView);
            imgType = itemView.findViewById(R.id.img_transaction_type);
            txtAccountName = itemView.findViewById(R.id.lbl_item_account_name);
            txtDate = itemView.findViewById(R.id.lbl_item_transaction_date);
            txtFormattedDate = itemView.findViewById(R.id.lbl_item_transaction_date_formated);
            txtDesc = itemView.findViewById(R.id.lbl_item_transaction_desc);
            txtCurrencySymbol = itemView.findViewById(R.id.lbl_item_currency_symbol);
            txtAmount = itemView.findViewById(R.id.lbl_item_transaction_amount);
            txtTransType = itemView.findViewById(R.id.lbl_item_transaction_type);
            checkBox = itemView.findViewById(R.id.checkbox_item);
            layout = itemView.findViewById(R.id.item_layout);

            layout.setOnLongClickListener(v -> {
                if (listener != null) listener.longClick(getLayoutPosition());
                return false;
            });
            btnOptions = itemView.findViewById(R.id.btn_options);
            btnOptions.setOnClickListener(v -> {
                if (listener != null) listener.clickOptions(getLayoutPosition());
            });
            checkBox.setOnClickListener(v -> {
                if (listener != null) listener.onSelect(v, getLayoutPosition());
            });
        }
    }
}
