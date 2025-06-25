package com.slynec.gomart.setup;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.slynec.gomart.R;

public class OTPDialog extends DialogFragment {

    private OnSubmitListener onSubmitListener;

    public interface OnSubmitListener {
        void onSubmit(String otp);
    }

    public void setOnSubmitListener(OnSubmitListener listener) {
        this.onSubmitListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_otp, null);

        EditText otpInput = view.findViewById(R.id.otp_input);
        Button submitButton = view.findViewById(R.id.submit_otp);

        submitButton.setOnClickListener(v -> {
            String otp = otpInput.getText().toString().trim();
            if (onSubmitListener != null && !otp.isEmpty()) {
                onSubmitListener.onSubmit(otp);
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
