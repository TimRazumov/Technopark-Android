package ru.mail.tp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {
    private static final String SIZE_BUNDLE_KEY = "size";
    private RecycleViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numCount;
        if (savedInstanceState != null) {
            numCount = savedInstanceState.getInt(SIZE_BUNDLE_KEY);
        } else {
            numCount = getResources().getInteger(R.integer.default_number_count);
        }
        mAdapter = new RecycleViewAdapter(numCount, getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = view.findViewById(R.id.add_number_button);
        button.setOnClickListener(v -> mAdapter.addValue());

        int colNum;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            colNum = getResources().getInteger(R.integer.portrait_column_count);
        } else {
            colNum = getResources().getInteger(R.integer.landscape_column_count);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(inflater.getContext(), colNum));
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SIZE_BUNDLE_KEY, mAdapter.getItemCount());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAdapter = null;
    }
}
