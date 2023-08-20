package com.b3to.newcash.Fragments;

import android.app.ActivityOptions;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Activities.AccountOverview.ActivityAccountOverview;
import com.b3to.newcash.Adapters.ADP_Account;
import com.b3to.newcash.Dialogs.DG_AddEditAccount;
import com.b3to.newcash.Dialogs.DG_BottomModelOptions;
import com.b3to.newcash.MainActivity;
import com.b3to.newcash.Models.Account;
import com.b3to.newcash.QueryModels.Q_Account;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.Widgets;
import com.b3to.newcash.ViewModels.VM_Account;

public class FragmentAccounts extends Fragment implements Widgets.IFragmentListener {

    private VM_Account vmAccount;
    private ADP_Account adp;
    private boolean isSelectable = false;

    public FragmentAccounts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        initView(view);
        initData(view);
        initEvents();
        return view;
    }

    void initView(View view) {

    }

    void initData(View view) {
        assert requireActivity() != null;
        Application app = requireActivity().getApplication();
        vmAccount = new VM_Account(app);
        adp = new ADP_Account(getContext());
        RecyclerView recycler = view.findViewById(R.id.recycler_main_accounts);
        vmAccount.readAllQ().observe(requireActivity(), list -> {
            adp.setList(list);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler.setAdapter(adp);
        });

    }

    void initEvents() {
        adp.setListener(this);
        MainActivity.setOnBtnAddClick(() -> {
            showPopup(null);
        });
    }

    public void showPopup(@Nullable Account model) {
        DG_AddEditAccount dialog;
        if (model != null) {
            dialog = new DG_AddEditAccount(vmAccount, model);
        } else {
            dialog = new DG_AddEditAccount(vmAccount);
        }
        dialog.show(getParentFragmentManager(), "AddEditAccount");
    }

    @Override
    public boolean isSelectable() {
        return isSelectable;
    }

    @Override
    public void click(int pos, Object obj) {
        Q_Account account = adp.getAccount(pos);
        Intent intent = new Intent(getContext(), ActivityAccountOverview.class);

        intent.putExtra("account_name", account.getName());
        intent.putExtra("account_type", account.getType_name());
        intent.putExtra("account_image", account.getImg());
        intent.putExtra("account_id", account.getId());

        ADP_Account.Holder holder = (ADP_Account.Holder) obj;

        var mPairs = new Pair[]{
                Pair.create(holder.txtName, "transition_account_name"),
                Pair.create(holder.img, "transition_account_image"),
        };
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(requireActivity(), mPairs);

        startActivity(intent,options.toBundle());
    }

    @Override
    public void longClick(int pos) {

    }

    @Override
    public void clickOptions(int pos) {
  DG_BottomModelOptions dialog = new DG_BottomModelOptions();
        dialog.setOnDelete(() -> {
            Account account = adp.getAccount(pos);
            vmAccount.delete(account);
        });
        dialog.setOnEdit(() -> showPopup(adp.getAccount(pos)));
        dialog.show(getParentFragmentManager(),"BOTTOM_SHEET");
    }

    @Override
    public void onSelect(View view, int pos) {

    }
}