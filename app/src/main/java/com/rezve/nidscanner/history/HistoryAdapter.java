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
import com.rezve.nidscanner.models.Nid;
import com.rezve.nidscanner.parser.DataParser;
import com.rezve.nidscanner.parser.NewNidDataParser;
import com.rezve.nidscanner.parser.OldNidDataParser;

import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Nid> historyList;
    private View.OnClickListener clickListener;
    private View.OnLongClickListener longClickListener;

    public HistoryAdapter(View.OnClickListener clickListener, View.OnLongClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public void setHistoryList(List<Nid> historyList) {
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
        Nid nid = historyList.get(position);
        holder.bind(nid);
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

        public void bind(Nid nid) {
            String date = Utils.dateToString(nid.getCreatedAt(), "MMM d, yyyy h:ma");
            this.nameTV.setText(nid.getName());
            this.nidNoTV.setText(getString(R.string.nid_no) + nid.getNidNo());
            this.dobTV.setText(getString(R.string.date_of_birth) + nid.getDateOfBirth());
            this.issueDateTV.setText(getString(R.string.issue_date) + nid.getIssueDate());
            this.scannedDateTV.setText(getString(R.string.scanned_at) + date);
            itemView.setTag(nid);
        }

        public String getString(int resourceId) {
            return itemView.getResources().getString(resourceId) + " ";
        }
    }
}
