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
import com.rezve.nidscanner.parser.DataParser;
import com.rezve.nidscanner.parser.NewNidDataParser;
import com.rezve.nidscanner.parser.OldNidDataParser;

import java.util.Date;
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
        Utils.CARD_TYPE cardType = Utils.getCardType(rawData);

        if (cardType == Utils.CARD_TYPE.SMART_NID_CARD) {
            NewNidDataParser parser = new NewNidDataParser(holder.itemView.getContext(), rawData);
            setCardData(holder, history, parser);
            holder.itemView.setTag(history);
        } else if (cardType == Utils.CARD_TYPE.OLD_NID_CARD) {
            OldNidDataParser parser = new OldNidDataParser(holder.itemView.getContext(), rawData);
            setCardData(holder, history, parser);
            holder.itemView.setTag(history);
        }
    }

    private void setCardData(ViewHolder holder, History history, DataParser parser) {
        String scannedAt = getScannedTime(holder, history.getCreatedAt());
        holder.nameTV.setText(parser.getName());
        holder.nidNoTV.setText(parser.getName());
        holder.dobTV.setText(parser.getDateOfBirth());
        holder.issueDateTV.setText(parser.getIssueDate());
        holder.scannedDateTV.setText(scannedAt);
    }

    private String getScannedTime(ViewHolder holder, Date date) {
        String dateStr = Utils.dateToString(date, "MMM d, yyyy h:ma");
        return holder.itemView.getResources().getString(R.string.scanned_at) + " " + dateStr;
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
