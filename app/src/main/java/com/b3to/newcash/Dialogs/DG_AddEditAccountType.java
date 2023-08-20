package com.b3to.newcash.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.b3to.newcash.Models.AccountType;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.DML;
import com.b3to.newcash.ViewModels.VM_AccountType;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class DG_AddEditAccountType extends BSDBase_MX_Height {
    TextInputEditText txtName;
    MaterialButton btnSave, btnCancel;
    AccountType model;
    DML.QUERY_TYPE query_type;
    VM_AccountType vmAccountType;

    public DG_AddEditAccountType(VM_AccountType vmCurrency) {
        this.vmAccountType = vmCurrency;
        query_type = DML.QUERY_TYPE.INSERT;
        this.model = new AccountType();
    }

    public DG_AddEditAccountType(VM_AccountType vmAccountType, AccountType model) {
        this(vmAccountType);
        this.model = model;
        query_type = DML.QUERY_TYPE.UPDATE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dg_add_edit_accounttype, container, false);
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
        if (vmAccountType == null) {
            assert requireActivity() != null;
            vmAccountType = new VM_AccountType(requireActivity().getApplication());
        }
        txtName = view.findViewById(R.id.input_account_type_name);

        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    private void initEvents() {
        btnCancel.setOnClickListener(v -> dismiss());
        btnSave.setOnClickListener(v -> {
            if (!checkErrors()) return;
            model.setName(String.valueOf(txtName.getText()));
            if (query_type == DML.QUERY_TYPE.INSERT) {
                vmAccountType.insert(model);
            } else if (query_type == DML.QUERY_TYPE.UPDATE) {
                vmAccountType.update(model);
            }
            dismiss();
        });

    }

    private void initData() {
        if (query_type == DML.QUERY_TYPE.UPDATE) {
            DML.AssignValue(txtName, model.getName());
        }
    }

    private Boolean checkErrors() {
        boolean valid;
        valid = !DML.isEmpty(txtName);
        return valid;
    }
}
