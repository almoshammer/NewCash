package com.b3to.newcash.Dialogs;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.b3to.newcash.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BSDBase_MX_Height extends BottomSheetDialogFragment {
    Dialog dialog;

    public BSDBase_MX_Height(){
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomDialog);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from((View) view.getParent());
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        assert dialog != null;
        LinearLayout layout = dialog.findViewById(R.id.bottom_sheet_layout);
        assert layout != null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes()
                .windowAnimations = R.style.DialogAnimation;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }
}
