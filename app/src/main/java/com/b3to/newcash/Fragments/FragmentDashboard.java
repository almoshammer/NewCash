package com.b3to.newcash.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Adapters.ADP_AccountTypeOverview;
import com.b3to.newcash.Adapters.ADP_Transaction;
import com.b3to.newcash.Dialogs.DG_AddEditAccount;
import com.b3to.newcash.Dialogs.DG_AddEditTransaction;
import com.b3to.newcash.MainActivity;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.DML;
import com.b3to.newcash.ViewModels.VM_AccountType;
import com.b3to.newcash.ViewModels.VM_Transaction;
import com.google.android.material.button.MaterialButton;

public class FragmentDashboard extends Fragment {

    TextView txtRecentCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(view);
        initDate(view);
        return view;
    }

    private void initView(View view) {
        MainActivity.setOnBtnAddClick(null);
        txtRecentCount = view.findViewById(R.id.txt_recent_count);
        MaterialButton btnAddTrans, btnAddAccount, btnSendNotify, btnTransition;

        btnAddTrans = view.findViewById(R.id.btn_add_transaction);
        btnAddAccount = view.findViewById(R.id.btn_add_account);
        btnSendNotify = view.findViewById(R.id.btn_send_attention);
        btnTransition = view.findViewById(R.id.btn_add_money_transaction);


        btnAddTrans.setOnClickListener(v -> new DG_AddEditTransaction(null).show(getParentFragmentManager(), "AddEditTransaction"));
        btnAddAccount.setOnClickListener(v -> new DG_AddEditAccount(null).show(getParentFragmentManager(), "AddEditAccount"));
        btnSendNotify.setOnClickListener(v->{
            
        });
        btnTransition.setOnClickListener(v->{
            
        });
        //TODO BTN SEND NOTIFY , BTN TRANSITION
    }

    private void initDate(View view) {
        Context context = getContext();
        if(context == null)return;
        RecyclerView recyclerAccountTypes = view.findViewById(R.id.recycler_dashboard_accounttypes);
        RecyclerView recyclerRecentTrans = view.findViewById(R.id.recycler_dashboard_recent_transactions);
        ADP_AccountTypeOverview adpAccountTypeOverview = new ADP_AccountTypeOverview(context);
        ADP_Transaction adpTransaction = new ADP_Transaction(context);

        new VM_AccountType(requireActivity().getApplication()).readAllQ().observe(requireActivity(), list -> {
            adpAccountTypeOverview.setList(list);
            recyclerAccountTypes.setLayoutManager(new LinearLayoutManager(context));
            recyclerAccountTypes.setAdapter(adpAccountTypeOverview);
        });

        int recent_count = DML.getInt(context, "recent_trans_count", 10);

        new VM_Transaction(requireActivity().getApplication()).readRecent(recent_count).observe(requireActivity(), list -> {
            adpTransaction.setList(list);
            recyclerRecentTrans.setLayoutManager(new LinearLayoutManager(context));
            recyclerRecentTrans.setAdapter(adpTransaction);
            int given_count = (list != null ? list.size() : 0);
            txtRecentCount.setText(String.format("(%s)", given_count));
        });

    }
}
