package ru.mail.tp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private final FragmentActivity mActivity;
    private int mNumCount;

    public RecycleViewAdapter(int numCount, FragmentActivity activity) {
        mNumCount = numCount;
        mActivity = activity;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int num) {
        num++;
        int color = NumberFragment.getColorByValue(num);
        holder.mNumber.setTextColor(mActivity.getResources().getColor(color));
        holder.mNumber.setText(String.valueOf(num));

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(NumberFragment.NUMBER_BUNDLE_KEY, Integer.parseInt(holder.mNumber.getText().toString()));

            NumberFragment fragment = new NumberFragment();
            fragment.setArguments(bundle);
            mActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return mNumCount;
    }

    public void addValue() {
        mNumCount += 1;
        notifyItemInserted(mNumCount);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumber = itemView.findViewById(R.id.recycler_number);
        }
    }
}
