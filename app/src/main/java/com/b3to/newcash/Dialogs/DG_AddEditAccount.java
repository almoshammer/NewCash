package com.b3to.newcash.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.b3to.newcash.Models.Account;
import com.b3to.newcash.Models.AccountType;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.DML;
import com.b3to.newcash.Tools.WidgetDropdown;
import com.b3to.newcash.Tools.Widgets;
import com.b3to.newcash.ViewModels.VM_Account;
import com.b3to.newcash.ViewModels.VM_AccountType;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class DG_AddEditAccount extends BSDBase_MX_Height {

    TextInputEditText txtName, txtPhone1, txtDesc;
    ImageView img;
    WidgetDropdown dropDown;
    MaterialButton btnSave, btnCancel;
    Account model;
    DML.QUERY_TYPE query_type;
    VM_Account vmAccount;

    public DG_AddEditAccount(VM_Account vmAccount) {
        this.vmAccount = vmAccount;
        query_type = DML.QUERY_TYPE.INSERT;
        this.model = new Account();
    }

    public DG_AddEditAccount(VM_Account vmAccount, Account model) {
        this(vmAccount);
        this.model = model;
        query_type = DML.QUERY_TYPE.UPDATE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dg_add_edit_account, container, false);
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
        if (vmAccount == null) {
            vmAccount = new VM_Account(requireActivity().getApplication());
        }

        txtName = view.findViewById(R.id.input_account_name);
        txtPhone1 = view.findViewById(R.id.input_account_phone1);
        txtDesc = view.findViewById(R.id.input_account_desc);
        dropDown = new WidgetDropdown(view, R.id.input_account_type);
        img = view.findViewById(R.id.img_account);

        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    private void initEvents() {
        btnCancel.setOnClickListener(v -> dismiss());
        btnSave.setOnClickListener(v -> {
            if (!checkErrors()) return;
            model.setName(String.valueOf(txtName.getText()));
            model.setPhone1(String.valueOf(txtPhone1.getText()));
            model.setDesc(String.valueOf(txtDesc.getText()));
            model.setType_id(dropDown.getId(dropDown.getSelectedIndex()));
            if (query_type == DML.QUERY_TYPE.INSERT) {
                vmAccount.insert(model);
            } else if (query_type == DML.QUERY_TYPE.UPDATE) {
                vmAccount.update(model);
            }
            dismiss();
        });

    }

    private void initData() {
        new VM_AccountType(requireActivity().getApplication()).readAll().observe(requireActivity(), list -> {
            ArrayList<WidgetDropdown.CList> clst = new ArrayList<>();
            for (AccountType type : list) {
                clst.add(new WidgetDropdown.CList(type.getId(), type.getName()));
            }
            dropDown.setList(clst);
            if (query_type == DML.QUERY_TYPE.UPDATE) {
                DML.AssignValue(txtName, model.getName());
                DML.AssignValue(txtPhone1, model.getPhone1());
                DML.AssignValue(txtDesc, model.getDesc());
                dropDown.setId(model.getType_id());
                initAccountImg();
            }
        });
    }
    private void initAccountImg(){
        if(model.getImg() == null || model.getImg().length == 0)
            Widgets.setImageTextThump(img,model.getName());
        else
            Glide.with(getContext()).load(model.getImg()).circleCrop().into(img);
    }

    private Boolean checkErrors() {
        boolean valid;
        valid = !DML.isEmpty(txtName);
        valid &= !DML.isEmpty(dropDown);
        return valid;
    }

}
