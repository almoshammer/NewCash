package com.b3to.newcash.Dialogs;

import static java.lang.String.valueOf;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.b3to.newcash.Models.Account;
import com.b3to.newcash.Models.Currency;
import com.b3to.newcash.Models.Transaction;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.DML;
import com.b3to.newcash.Tools.WidgetDropdown;
import com.b3to.newcash.ViewModels.VM_Account;
import com.b3to.newcash.ViewModels.VM_Currency;
import com.b3to.newcash.ViewModels.VM_Transaction;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;


public class DG_AddEditTransaction extends BSDBase_MX_Height {
    TextInputEditText txtDesc, txtDate, txtAmount;
    MaterialButton btnSave, btnCancel;
    Transaction model;
    DML.QUERY_TYPE query_type;
    VM_Transaction vmTransaction;
    WidgetDropdown dropAccounts, dropCurrencies;
    ThemedToggleButtonGroup groupType;
    ThemedButton btnCredit, btnDebit;

    List<ThemedButton> allButtons;


    public DG_AddEditTransaction(VM_Transaction vmTransaction) {
        this.vmTransaction = vmTransaction;
        query_type = DML.QUERY_TYPE.INSERT;
        this.model = new Transaction();
    }

    public DG_AddEditTransaction(VM_Transaction vmTransaction, Transaction model) {
        this(vmTransaction);
        this.model = model;
        query_type = DML.QUERY_TYPE.UPDATE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dg_add_edit_transaction, container, false);
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

        if (vmTransaction == null) {
            vmTransaction = new VM_Transaction(requireActivity().getApplication());
        }
        txtDesc = view.findViewById(R.id.input_transaction_desc);
        txtDate = view.findViewById(R.id.input_transaction_date);
        txtAmount = view.findViewById(R.id.input_transaction_amount);

        dropAccounts = new WidgetDropdown(view, R.id.input_account);
        dropCurrencies = new WidgetDropdown(view, R.id.input_currency);

        groupType = view.findViewById(R.id.group_transaction_type);
        allButtons = groupType.getButtons();
        btnCredit = view.findViewById(R.id.btn_input_credit);
        btnDebit = view.findViewById(R.id.btn_input_debit);

        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    private void initEvents() {
        btnCancel.setOnClickListener(v -> dismiss());
        btnSave.setOnClickListener(v -> {
            if (!checkErrors()) return;
            model.setAccount_id(dropAccounts.getSelectedId());
            model.setCurrency_id(dropCurrencies.getSelectedId());

            model.setType(getTransType());

            // begin set date
            Calendar calendar = Calendar.getInstance();
            final int minute = calendar.get(Calendar.MINUTE);
            final int hour = calendar.get(Calendar.HOUR_OF_DAY);
            final int millie = 0;

            String dStr = String.format("%s %s:%s:%s", txtDate.getText().toString().trim(), hour, minute, millie);
            long timestamp = DML.toTimeStamp(dStr, DML.DATE_FORMAT);

            model.setDate(timestamp);
            // end set date
            model.setAmount(Double.parseDouble(valueOf(txtAmount.getText())));
            model.setDesc(valueOf(txtDesc.getText()));

            DML.putLong(getContext(), "currency_id", model.getCurrency_id());

            if (query_type == DML.QUERY_TYPE.INSERT) {
                vmTransaction.insert(model);
            } else if (query_type == DML.QUERY_TYPE.UPDATE) {
                vmTransaction.update(model);
            }
            dismiss();
        });


        Calendar calendar = Calendar.getInstance();
        txtDate.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    (view, year, month, dayOfMonth) -> {

                        String dStr = (dayOfMonth) + "-" + (month + 1) + "-" + year;
                        txtDate.setText(dStr);
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
    }

    private int getTransType() {
        if (btnDebit.isSelected()) return 0;
        if (btnCredit.isSelected()) return 1;
        return -1;
    }

    private void setTransType(int type) {
        if (type == 0) {
            groupType.selectButton(R.id.btn_input_debit);
        } else if (type == 1) {
            groupType.selectButton(R.id.btn_input_credit);
        }
    }

    private void initData() {

        //accounts list
        new VM_Account(requireActivity().getApplication()).readAll().observe(requireActivity(), list -> {

            ArrayList<WidgetDropdown.CList> clst = new ArrayList<>();
            for (Account type : list) {
                clst.add(new WidgetDropdown.CList(type.getId(), type.getName()));
            }
            dropAccounts.setList(clst);


            if (query_type == DML.QUERY_TYPE.UPDATE) {
                dropAccounts.setId(model.getAccount_id());
                DML.AssignValue(txtDesc, model.getDesc());
                DML.AssignValue(txtDate, DML.toDate(model.getDate()));
                DML.AssignValue(txtAmount, model.getAmount() + "");
                setTransType(model.getType());
            } else if (query_type == DML.QUERY_TYPE.INSERT) {
                txtDate.setText(DML.getCurrDate());
                dropCurrencies.setId(DML.getLong(getContext(), "currency_id"));
            }

        });
        //currencies list
        new VM_Currency(requireActivity().getApplication()).readAll().observe(requireActivity(), list -> {
            ArrayList<WidgetDropdown.CList> clst = new ArrayList<>();
            for (Currency type : list) {
                clst.add(new WidgetDropdown.CList(type.getId(), type.getSymbol()));
            }
            dropCurrencies.setList(clst);
            if (query_type == DML.QUERY_TYPE.UPDATE) dropCurrencies.setId(model.getCurrency_id());
        });
    }

    private Boolean checkErrors() {
        boolean valid;
        valid = !DML.isEmpty(txtDesc);
        valid &= !DML.isEmpty(dropAccounts);
        valid &= !DML.isEmpty(dropCurrencies);
        valid &= !DML.isEmpty(txtAmount);
        valid &= !DML.isEmpty(groupType);
        return valid;
    }

}
