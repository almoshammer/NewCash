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

import com.animsh.animatedcheckbox.AnimatedCheckBox;
import com.b3to.newcash.Adapters.ADP_Transaction;
import com.b3to.newcash.Dialogs.DG_AddEditTransaction;
import com.b3to.newcash.Dialogs.DG_BottomModelOptions;
import com.b3to.newcash.MainActivity;
import com.b3to.newcash.Models.Transaction;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.Widgets;
import com.b3to.newcash.ViewModels.VM_Transaction;

import java.util.ArrayList;

public class FragmentTransactions extends Fragment implements Widgets.IFragmentListener {

    VM_Transaction vmTransaction;
    ADP_Transaction adp;
    //begin selection variables
    private ArrayList<Transaction> selectedList = new ArrayList<>();
    private boolean is_selectable = false;
    private int selected_count = 0;
    //end selection variables

    public FragmentTransactions() {
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
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        Application app = requireActivity().getApplication();
        vmTransaction = new VM_Transaction(app);
        initData(view);
        initEvents();
        return view;
    }

    void initData(View view) {
        adp = new ADP_Transaction(getContext());
        RecyclerView recycler = view.findViewById(R.id.recycler_main_transactions);
        vmTransaction.readAllR().observe(requireActivity(), list -> {
            adp.setList(list);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler.setAdapter(adp);
        });


    }

    void initEvents() {

        adp.setListener(this);
        MainActivity.setOnBtnAddClick(() -> showPopup(null));
    }

    public void showPopup(@Nullable Transaction model) {
        DG_AddEditTransaction dialog;
        if (model != null)
            dialog = new DG_AddEditTransaction(vmTransaction, model);
        else {
            dialog = new DG_AddEditTransaction(vmTransaction); // add new
        }
        dialog.show(getParentFragmentManager(), "AddEditTransaction");
    }

    @Override
    public boolean isSelectable() {
        return this.is_selectable;
    }

    @Override
    public void click(int pos, Object obj) {

    }

    @Override
    public void longClick(int pos) {
        MainActivity.crudToolbar.show();
        MainActivity.crudToolbar.setOnClose(() -> {
            is_selectable = false;
            selected_count = 0;
            MainActivity.crudToolbar.setCount(0);
            selectedList.clear();
            adp.notifyDataSetChanged();
        });
        is_selectable = true;
        adp.notifyDataSetChanged();
    }

    @Override
    public void clickOptions(int pos) {
        DG_BottomModelOptions dialog = new DG_BottomModelOptions();
        dialog.setOnDelete(() -> {
            Transaction account = adp.getTransaction(pos);
            vmTransaction.delete(account);
        });
        dialog.setOnEdit(() -> showPopup(adp.getTransaction(pos)));
        dialog.show(getParentFragmentManager(), "BOTTOM_SHEET");
    }

    @Override
    public void onSelect(View view, int pos) {
        if (((AnimatedCheckBox) view).isChecked()) {
            selectedList.add(adp.getTransaction(pos));
            selected_count++;
            MainActivity.crudToolbar.setCount(selected_count);
        } else {
            selectedList.remove(adp.getTransaction(pos));
            selected_count--;
            MainActivity.crudToolbar.setCount((selected_count));
        }
    }
}