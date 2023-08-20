package com.b3to.newcash.Dialogs;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.b3to.newcash.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DG_BottomModelOptions extends BottomSheetDialogFragment {
    LinearLayout btnEdit, btnDelete;
    private IAction mDelete;
    private IAction mEdit;

    public DG_BottomModelOptions() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dg_bottom_model_options, container, false);
        initView(view);
        initEvents();
        return view;
    }

    private void initBlur(View view) {
//        BlurView blurView = view.findViewById(R.id.layout_bottom_blur);
//        float radius = 5f;
//        View decorView = requireActivity().getWindow().getDecorView();
//        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
//        Drawable windowBackground = decorView.getBackground();
//        blurView.setupWith(rootView, new RenderScriptBlur(getContext())) // or RenderEffectBlur
//                .setFrameClearDrawable(windowBackground) // Optional
//                .setBlurRadius(radius);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes()
                .windowAnimations = R.style.DialogAnimation;
    }

    private void initView(View view) {
        btnEdit = view.findViewById(R.id.btn_edit);
        btnDelete = view.findViewById(R.id.btn_delete);
    }

    private void initEvents() {
        if (mDelete != null) {
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.are_you_sure_of_deletion_cannot_undo_this_process);
                builder.setTitle(R.string.attention);
                builder.setNegativeButton(R.string.no, (dialog, which) -> dismiss())
                        .setPositiveButton(R.string.yes_iam_sure, (dialog, which) -> {
                            mDelete.action();
                            dismiss();
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            });
        }
        if (mEdit != null) {
            btnEdit.setVisibility(View.VISIBLE);
            btnEdit.setOnClickListener(v -> {
                mEdit.action();
                dismiss();
            });
        }
    }

    public void setOnEdit(IAction mEdit) {
        this.mEdit = mEdit;
    }

    public void setOnDelete(IAction mDelete) {
        this.mDelete = mDelete;
    }

    public interface IAction {
        void action();
    }

    public interface IOptionClick {
        void options(int position);
    }
}
