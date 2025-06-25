package com.slynec.gomart.customer.ui.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.view.MenuInflater;

import com.slynec.gomart.R;

public class navigation_settings extends Fragment {

    private NavigationSettingsViewModel mViewModel;

    public static navigation_settings newInstance() {
        return new navigation_settings();
    }

    Button customButton;
    Switch switchInsideButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the views
        customButton = view.findViewById(R.id.customButton);
        switchInsideButton = view.findViewById(R.id.switchInsideButton);

        customButton.setOnClickListener(v -> {
            // Handle button click
            boolean isSwitchChecked = switchInsideButton.isChecked();
            switchInsideButton.setChecked(!isSwitchChecked);
        });


        // Access the toolbar and disable the back button
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NavigationSettingsViewModel.class);
        // TODO: Use the ViewModel
    }
}
