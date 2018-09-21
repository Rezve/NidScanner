package com.rezve.nidscanner.history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rezve.nidscanner.R;
import com.rezve.nidscanner.Utils;
import com.rezve.nidscanner.models.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<History> historyList;
    private View.OnClickListener clickListener;
    private View.OnLongClickListener longClickListener;

    public HistoryAdapter(View.OnClickListener clickListener, View.OnLongClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = historyList.get(position);
        holder.nameTV.setText(history.getName());
        holder.dateTV.setText(Utils.dateToString(history.getCreatedAt(), "d MMM yyyy"));
        holder.itemView.setTag(history);
    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV;
        public TextView dateTV;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            itemView.setOnClickListener(clickListener);
            itemView.setOnLongClickListener(longClickListener);
        }
    }
}
