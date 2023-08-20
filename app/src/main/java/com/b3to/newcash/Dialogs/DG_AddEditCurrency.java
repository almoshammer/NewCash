package com.b3to.newcash.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.b3to.newcash.Models.Currency;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.DML;
import com.b3to.newcash.ViewModels.VM_Account;
import com.b3to.newcash.ViewModels.VM_Currency;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class DG_AddEditCurrency extends BSDBase_MX_Height {
    TextInputEditText txtName, txtSymbol;
    MaterialButton btnSave, btnCancel;
    Currency model;
    DML.QUERY_TYPE query_type;
    VM_Currency vmCurrency;

    public DG_AddEditCurrency(VM_Currency vmCurrency) {
        this.vmCurrency = vmCurrency;
        query_type = DML.QUERY_TYPE.INSERT;
        this.model = new Currency();
    }

    public DG_AddEditCurrency(VM_Currency vmCurrency, Currency model) {
        this(vmCurrency);
        this.model = model;
        query_type = DML.QUERY_TYPE.UPDATE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dg_add_edit_currency, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initEvents();
    }

    private void initView(View view) {
        if (vmCurrency == null) {
            vmCurrency = new VM_Currency(requireActivity().getApplication());
        }
        txtName = view.findViewById(R.id.input_currency_name);
        txtSymbol = view.findViewById(R.id.input_currency_symbol);

        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    private void initEvents() {
        btnCancel.setOnClickListener(v -> dismiss());
        btnSave.setOnClickListener(v -> {
            if (!checkErrors()) return;
            model.setName(String.valueOf(txtName.getText()));
            model.setSymbol(String.valueOf(txtSymbol.getText()));
            if (query_type == DML.QUERY_TYPE.INSERT) {
                vmCurrency.insert(model);
            } else if (query_type == DML.QUERY_TYPE.UPDATE) {
                vmCurrency.update(model);
            }
            dismiss();
        });

    }

    private void initData() {
        if (query_type == DML.QUERY_TYPE.UPDATE) {
            DML.AssignValue(txtName, model.getName());
            DML.AssignValue(txtSymbol, model.getSymbol());
        }
    }

    private Boolean checkErrors() {
        boolean valid;
        valid = !DML.isEmpty(txtName);
        valid &= !DML.isEmpty(txtSymbol);
        return valid;
    }
}
