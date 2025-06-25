package com.slynec.gomart.customer.ui.profile;

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
import android.widget.EditText;
import android.widget.ScrollView;
import com.slynec.gomart.R;

public class navigation_profile extends Fragment {

    private NavigationProfileViewModel mViewModel;
    private EditText textField1, textField2, textField3,
            textField4, textField5, textField6, textField7, textField8, text_area;
    private Button toggleButton, toggleButton1, toggleButton2, toggleButton3;
    private boolean areFieldsVisible1 = true;
    private boolean areFieldsVisible2 = true;
    private boolean areFieldsVisible3 = true;
    private boolean areFieldsVisible4 = true;

    public static navigation_profile newInstance() {
        return new navigation_profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_navigation_profile, container, false);

        // Initialize views
        textField1 = view.findViewById(R.id.textField1);
        textField2 = view.findViewById(R.id.textField2);
        textField3 = view.findViewById(R.id.textField3);
        toggleButton = view.findViewById(R.id.toggleButton);

        textField4 = view.findViewById(R.id.textField4);
        textField5 = view.findViewById(R.id.textField5);
        textField6 = view.findViewById(R.id.textField6);
        toggleButton1 = view.findViewById(R.id.toggleButton1);

        textField7 = view.findViewById(R.id.textField7);
        textField8 = view.findViewById(R.id.textField8);
        toggleButton2 = view.findViewById(R.id.toggleButton2);

        text_area = view.findViewById(R.id.text_area);
        toggleButton3 = view.findViewById(R.id.toggleButton3);

        // Set button click listener for toggleButton
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField1.setVisibility(areFieldsVisible1 ? View.GONE : View.VISIBLE);
                textField2.setVisibility(areFieldsVisible1 ? View.GONE : View.VISIBLE);
                textField3.setVisibility(areFieldsVisible1 ? View.GONE : View.VISIBLE);
                areFieldsVisible1 = !areFieldsVisible1;
            }
        });

        // Set button click listener for toggleButton1
        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField4.setVisibility(areFieldsVisible2 ? View.GONE : View.VISIBLE);
                textField5.setVisibility(areFieldsVisible2 ? View.GONE : View.VISIBLE);
                textField6.setVisibility(areFieldsVisible2 ? View.GONE : View.VISIBLE);
                areFieldsVisible2 = !areFieldsVisible2;
            }
        });

        // Set button click listener for toggleButton2
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField7.setVisibility(areFieldsVisible3 ? View.GONE : View.VISIBLE);
                textField8.setVisibility(areFieldsVisible3 ? View.GONE : View.VISIBLE);
                areFieldsVisible3 = !areFieldsVisible3;
            }
        });

        // Set button click listener for toggleButton3
        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_area.setVisibility(areFieldsVisible4 ? View.GONE : View.VISIBLE);
                areFieldsVisible4 = !areFieldsVisible4;
            }
        });

        // Return the root view wrapped in ScrollView to ensure scrollable content
        ScrollView scrollView = new ScrollView(getContext());
        scrollView.addView(view);
        return scrollView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        mViewModel = new ViewModelProvider(this).get(NavigationProfileViewModel.class);
        // TODO: Use the ViewModel
    }
}
