package com.rezve.nidscanner.history;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rezve.nidscanner.R;
import com.rezve.nidscanner.models.History;

import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private HistoryAdapter adapter;
    private RecyclerView recyclerView;
    private HistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setRecyclerView();
    }

    private void setRecyclerView() {
        adapter = new HistoryAdapter(onClickListener, onLongClickListener);
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        viewModel.getHistoryList().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(@Nullable List<History> histories) {
                adapter.setHistoryList(histories);
            }
        });
        recyclerView = findViewById(R.id.historyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            History history = (History) v.getTag();
            Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
            intent.putExtra("history", history);
            startActivity(intent);
        }
    };

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            final History history = (History) v.getTag();
            String msg = "Want to delete "+history.getName() +"  ?";
            Snackbar.make(v, msg , Snackbar.LENGTH_LONG).setAction("Delete", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.delete(history);
                            Snackbar.make(v, "Name Deleted", Snackbar.LENGTH_SHORT).show();
                        }
                    }).show();
            return true;
        }
    };

    public void insert(View view) {
        History history = new History("Name", "detailsss", new Date());
        viewModel.insert(history);
    }

}
