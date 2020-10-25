package ru.mail.tp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class NumberFragment extends Fragment {
    public static final String NUMBER_BUNDLE_KEY = "number";

    public static int getColorByValue(int num) {
        if (num % 2 == 0) {
            return R.color.red;
        }
        return R.color.blue;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_number, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // int num = savedInstanceState.getInt(NUMBER_BUNDLE_KEY);
        Bundle arg = getArguments();
        if (arg == null) {
            return;
        }
        int num = getArguments().getInt(NUMBER_BUNDLE_KEY);

        TextView textView = Objects.requireNonNull(getActivity()).findViewById(R.id.fragment_number);
        textView.setText(String.valueOf(num));

        int color = getColorByValue(num);
        textView.setTextColor(getResources().getColor(color));
    }
}
