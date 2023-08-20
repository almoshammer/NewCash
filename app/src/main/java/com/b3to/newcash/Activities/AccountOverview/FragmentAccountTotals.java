package com.b3to.newcash.Activities.AccountOverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Adapters.ADP_Account;
import com.b3to.newcash.Adapters.ADP_AccountType;
import com.b3to.newcash.Adapters.ADP_AccountTypeOverview;
import com.b3to.newcash.R;
import com.b3to.newcash.ViewModels.VM_AccountType;

public class FragmentAccountTotals extends Fragment {

    private final long account_id;

    public FragmentAccountTotals(long account_id) {
        this.account_id = account_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_totals, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_account_totals);
        ADP_AccountTypeOverview adpAccountTypeOverview = new ADP_AccountTypeOverview(getContext());

        new VM_AccountType(requireActivity().getApplication()).readAllQ(account_id).observe(requireActivity(), list -> {
            adpAccountTypeOverview.setList(list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adpAccountTypeOverview);
        });
    }
}