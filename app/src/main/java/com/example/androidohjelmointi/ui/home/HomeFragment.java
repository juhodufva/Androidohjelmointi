package com.example.androidohjelmointi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidohjelmointi.GameActivity;
import com.example.androidohjelmointi.R;
import com.example.androidohjelmointi.YTJ.DataActivity;
import com.example.androidohjelmointi.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    public static final String TAG = "MainActivity";
    private Button testBtn;
    private TextView helloText;
    private Button playBtn;
    private FragmentHomeBinding binding;
    private Button searhButton;
    private EditText searchCompany;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView2;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        testBtn = root.findViewById(R.id.button_test);
        helloText = root.findViewById(R.id.textView2);
        playBtn = root.findViewById(R.id.button_play);
        searhButton = root.findViewById(R.id.button_search);
        searchCompany= root.findViewById(R.id.searchCompany);

        searhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DataActivity.class);
                i.putExtra(searchCompany.getText().toString(), "This is the value for activity");
                startActivity(i);
            }
        });
        testBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                helloText.setText(R.string.new_welcome_text);
                Log.e(TAG, "Button clicked");
                // Code here executes on main thread after user presses button
            }
        });
        helloText.findViewById(R.id.textView2);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Play button clicked");
                Intent i = new Intent(getActivity(), GameActivity.class);
                startActivity(i);

            }
        });
        return root;





    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}