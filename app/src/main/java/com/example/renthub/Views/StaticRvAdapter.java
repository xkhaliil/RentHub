package com.example.renthub.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.renthub.R;
import com.example.renthub.Views.Models.StaticRvModel;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder> {
    private ArrayList<StaticRvModel> items;
    private OnItemClickListener onItemClickListener;
    int row_index = -1;

    public StaticRvAdapter(ArrayList<StaticRvModel> items) {
        this.items = items;
    }

    // Interface for item click events
    public interface OnItemClickListener {
        void onItemClick(StaticRvModel item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false);
        return new StaticRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(currentItem);
                }
            }
        });
        if (row_index == position) {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
        } else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
