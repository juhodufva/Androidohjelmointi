package com.example.androidohjelmointi.ui.notifications;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidohjelmointi.R;
import com.example.androidohjelmointi.databinding.FragmentNotificationsBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class NotificationsFragment extends Fragment {


    private FragmentNotificationsBinding binding;

    public CountDownTimer cdt;

    public int timerTime;

    public boolean isTimerPaused = false;
    public long timeLeftInMillis;

    public static final String TAG = "Timer";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final MaterialButtonToggleGroup toggleBtn = root.findViewById(R.id.toggleButton);
        final TextView timerCountDown = root.findViewById(R.id.textviewnotifications);
        final Button button4 = root.findViewById(R.id.button_start);
        final Button button5 = root.findViewById(R.id.button_pause);
        final Button button6 = root.findViewById(R.id.button_stop);

        final NumberPicker picker = root.findViewById(R.id.numberpicker_main_picker);
        picker.setMaxValue(60);
        picker.setMinValue(0);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                // Code here executes on main thread after user selects value
                timerTime = picker.getValue();
                Log.d(TAG, timerTime + "");
                timerTime = timerTime * 1000;
            }
        });


        String[] strArray = new String[501];
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = i + "s";
        }
        //final TextView textView = binding.textNotifications;
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        picker.setDisplayedValues(strArray);


        toggleBtn.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        if (isChecked) {
                            if (checkedId == R.id.button_start) {
                                // start timing here
                                if (isTimerPaused) {
                                    cdt = new CountDownTimer(timeLeftInMillis, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            timeLeftInMillis = millisUntilFinished;
                                            timerCountDown.setText("" + millisUntilFinished / 1000 + " s");
                                        }

                                        public void onFinish() {
                                            // Set the text to show the end message
                                            timerCountDown.setText("done!");

                                            // Create a rotate animation
                                            Animation rotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);

                                            // Apply the animation to the TextView
                                            timerCountDown.startAnimation(rotateAnim);

                                            // Set isTimerPaused to false
                                            isTimerPaused = false;
                                        }
                                    }.start();
                                    isTimerPaused = false;
                                } else {
                                    cdt = new CountDownTimer(timerTime, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            timeLeftInMillis = millisUntilFinished;
                                            timerCountDown.setText("" + millisUntilFinished / 1000 + " s");
                                        }

                                        public void onFinish() {
                                            // Set the text to show the end message
                                            timerCountDown.setText("done!");

                                            // Create a rotate animation
                                            Animation rotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);

                                            // Apply the animation to the TextView
                                            timerCountDown.startAnimation(rotateAnim);

                                            // Set isTimerPaused to false
                                            isTimerPaused = false;
                                        }
                                    }.start();
                                }
                            } else if (checkedId == R.id.button_stop) {
                                // stop timing here
                                if (cdt != null) {
                                    cdt.cancel();
                                    isTimerPaused = false;
                                    timeLeftInMillis = 0;
                                    timerCountDown.setText("0");
                                }
                            } else if (checkedId == R.id.button_pause) {
                                // pause timing here
                                if (cdt != null) {
                                    if (!isTimerPaused) {
                                        cdt.cancel();
                                        isTimerPaused = true;
                                    } else {
                                        cdt = new CountDownTimer(timeLeftInMillis, 1000) {
                                            public void onTick(long millisUntilFinished) {
                                                timeLeftInMillis = millisUntilFinished;
                                                timerCountDown.setText("" + millisUntilFinished / 1000 + " s");
                                            }

                                            public void onFinish() {
                                                // Set the text to show the end message
                                                timerCountDown.setText("done!");

                                                // Create a rotate animation
                                                Animation rotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);

                                                // Apply the animation to the TextView
                                                timerCountDown.startAnimation(rotateAnim);




                                                // Set isTimerPaused to false
                                                isTimerPaused = false;
                                            }
                                        }.start();
                                        isTimerPaused = false;
                                    }
                                }
                            }
                        }
                    }
                });


        return root;
    }

    private class FadeOutAnimation extends Animation {
        private View view;
        private int duration;

        public FadeOutAnimation(View view, int duration) {
            this.view = view;
            this.duration = duration;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            view.setAlpha(1 - interpolatedTime);
        }

        @Override
        public boolean willChangeBounds() {
            return false;
        }

        @Override
        public boolean willChangeTransformationMatrix() {
            return false;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}