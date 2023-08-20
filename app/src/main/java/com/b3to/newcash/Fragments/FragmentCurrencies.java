package com.b3to.newcash.Fragments;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Adapters.ADP_Currency;
import com.b3to.newcash.Dialogs.DG_AddEditCurrency;
import com.b3to.newcash.Dialogs.DG_BottomModelOptions;
import com.b3to.newcash.MainActivity;
import com.b3to.newcash.Models.Currency;
import com.b3to.newcash.R;
import com.b3to.newcash.ViewModels.VM_Currency;

public class FragmentCurrencies extends Fragment {
    VM_Currency vmCurrency;
    ADP_Currency adp;

    public FragmentCurrencies() {
        // Required empty public constructor
    }

    public static FragmentCurrencies newInstance(String... params) {
        FragmentCurrencies fragment = new FragmentCurrencies();
        Bundle args = new Bundle();

        args.putStringArray("args", params);
        fragment.setArguments(args);

        return fragment;
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
        Application app = requireActivity().getApplication();
        vmCurrency = new VM_Currency(app);
        initData(view);
        initEvents();
        return view;
    }

    void initData(View view) {
        adp = new ADP_Currency(getContext());
        RecyclerView recycler = view.findViewById(R.id.recycler_main_currencies);
        vmCurrency.readAll().observe(requireActivity(), list -> {
            adp.setList(list);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler.setAdapter(adp);
        });
    }

    void initEvents() {
        adp.setOnOptionsClick(position -> {
            DG_BottomModelOptions dialog = new DG_BottomModelOptions();
            dialog.setOnDelete(() -> {
                Currency model = adp.getCurrency(position);
                vmCurrency.delete(model);
            });
            dialog.setOnEdit(() -> showPopup(adp.getCurrency(position)));
            dialog.show(getParentFragmentManager(),"BOTTOM_SHEET");
        });
        MainActivity.setOnBtnAddClick(() -> showPopup(null));
    }

    public void showPopup(@Nullable Currency model) {
        DG_AddEditCurrency dialog;
        if (model != null)
            dialog = new DG_AddEditCurrency(vmCurrency, model);
        else {
            dialog = new DG_AddEditCurrency(vmCurrency);
        }
        dialog.show(getParentFragmentManager(), "AddEditCurrency");
    }
}
