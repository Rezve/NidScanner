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
        String rawData = history.getDetails();
        String scannedAt = Utils.dateToString(history.getCreatedAt(), "dd MMMM yyyy");
        Utils.CARD_TYPE cardType = Utils.getCardType(rawData);

        if (cardType != Utils.CARD_TYPE.UNKNOWN) {
            holder.nameTV.setText(Utils.getName(cardType, rawData));
            holder.nidNoTV.setText(holder.getString(R.string.nid_no) + Utils.getNidNo(cardType, rawData));
            holder.dobTV.setText(holder.getString(R.string.date_of_birth) + Utils.getDateOfBirth(cardType, rawData));
            holder.issueDateTV.setText(holder.getString(R.string.issue_date) + Utils.getIssueDate(cardType, rawData));
            holder.scannedDateTV.setText(holder.getString(R.string.scanned_at) +scannedAt);
            holder.itemView.setTag(history);
        }
    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV;
        public TextView nidNoTV;
        public TextView dobTV;
        public TextView issueDateTV;
        public TextView scannedDateTV;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            nidNoTV = itemView.findViewById(R.id.nidNoTV);
            dobTV = itemView.findViewById(R.id.dobTV);
            issueDateTV = itemView.findViewById(R.id.issueDateTV);
            scannedDateTV = itemView.findViewById(R.id.scannedDateTV);
            itemView.setOnClickListener(clickListener);
            itemView.setOnLongClickListener(longClickListener);
        }

        public String getString(int resourceId) {
            return itemView.getResources().getString(resourceId) + " ";
        }
    }
}
