package com.b3to.newcash.Fragments;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Adapters.ADP_AccountType;
import com.b3to.newcash.Dialogs.DG_AddEditAccountType;
import com.b3to.newcash.Dialogs.DG_BottomModelOptions;
import com.b3to.newcash.MainActivity;
import com.b3to.newcash.Models.AccountType;
import com.b3to.newcash.R;
import com.b3to.newcash.ViewModels.VM_AccountType;

public class FragmentAccountTypes extends Fragment {
    VM_AccountType vmAccountType;
    ADP_AccountType adp;
    Toolbar toolbar;

    public FragmentAccountTypes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_currencies, container, false);
        assert requireActivity() != null;
        assert requireActivity().getApplication() != null;
        Application app = requireActivity().getApplication();
        vmAccountType = new VM_AccountType(app);
        initData(view);
        initEvents();
        return view;
    }

    void initData(View view) {
        adp = new ADP_AccountType(getContext());
        RecyclerView recycler = view.findViewById(R.id.recycler_main_currencies);
        vmAccountType.readAll().observe(requireActivity(), list -> {
            adp.setList(list);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler.setAdapter(adp);
        });
    }

    void initEvents() {
        adp.setOnOptionsClick(position -> {
            assert getContext() != null;
            DG_BottomModelOptions dialog = new DG_BottomModelOptions();
            dialog.setOnDelete(() -> {
                AccountType model = adp.getAccountType(position);
                vmAccountType.delete(model);
            });
            dialog.setOnEdit(() -> showPopup(adp.getAccountType(position)));
            dialog.show(getParentFragmentManager(),"BOTTOM_SHEET");
        });
        MainActivity.setOnBtnAddClick(() -> showPopup(null));
    }

    public void showPopup(@Nullable AccountType model) {
        DG_AddEditAccountType dialog;
        if (model != null)
            dialog = new DG_AddEditAccountType(vmAccountType, model);
        else {
            dialog = new DG_AddEditAccountType(vmAccountType);
        }
        dialog.show(getParentFragmentManager(), "AddEditCurrency");
    }
}
