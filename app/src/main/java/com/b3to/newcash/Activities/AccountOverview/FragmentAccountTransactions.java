package com.b3to.newcash.Activities.AccountOverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Adapters.ADP_Transaction;
import com.b3to.newcash.R;
import com.b3to.newcash.ViewModels.VM_Account;

public class FragmentAccountTransactions extends Fragment {

    private final long account_id;

    public FragmentAccountTransactions(long account_id) {
        // Required empty public constructor
        this.account_id = account_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_transactions, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_account_transactions);
        ADP_Transaction adp = new ADP_Transaction(getContext());
        adp.setAccount_name_visible(false);

        FragmentActivity activityCompat = requireActivity();

        assert activityCompat != null;
        new VM_Account(activityCompat.getApplication()).readTransactions(this.account_id).observe(requireActivity(), list -> {

            adp.setList(list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adp);
        });
    }
}