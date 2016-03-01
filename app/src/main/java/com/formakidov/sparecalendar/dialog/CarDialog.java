package com.formakidov.sparecalendar.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.listener.SimpleTextWatcher;
import com.formakidov.sparecalendar.model.Car;

public class CarDialog extends DialogFragment implements Constants {
    private DialogCallback mCallback;
    private EditText etName;
    private EditText etComment;
    private TextInputLayout tlName;
    private boolean isEdit;

    public interface DialogCallback {
        void onCarChanged(Car changedItem);
        void onCarCreated(Car newItem);
    }

    private final MaterialDialog.ButtonCallback mButtonCallback = new MaterialDialog.ButtonCallback() {
        @Override
        public void onPositive(MaterialDialog dialog) {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                tlName.setError(getString(R.string.error_empty_name));
                return;
            }
            String comment = etComment.getText().toString().trim();
            if (isEdit) {
                mCallback.onCarChanged(new Car(name, comment, 11, System.currentTimeMillis())); // TODO: 02.03.2016
            } else {
                mCallback.onCarCreated(new Car(name, comment, 11, System.currentTimeMillis())); // TODO: 02.03.2016
            }
            dialog.dismiss();
        }

        @Override
        public void onNegative(MaterialDialog materialDialog) {
            materialDialog.dismiss();
        }
    };

    public void setListener(DialogCallback callback) {
        this.mCallback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .callback(mButtonCallback)
                .autoDismiss(false)
                .customView(R.layout.car_dialog, false)
                .positiveText(isEdit ? R.string.save : R.string.add)
                .negativeText(android.R.string.cancel)
                .build();

        View customView = dialog.getCustomView();
        etName = (EditText) customView.findViewById(R.id.et_name);
        etComment = (EditText) customView.findViewById(R.id.et_comment);
        tlName = (TextInputLayout) customView.findViewById(R.id.tl_name);
        Bundle args = getArguments();
        if (null != args) {
            isEdit = true;
            etName.setText(args.getString(CAR_NAME, EMPTY_STRING));
            etComment.setText(args.getString(CAR_COMMENT, EMPTY_STRING));
        }
        etName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    tlName.setError(null);
                }
            }
        });

        return dialog;
    }
}