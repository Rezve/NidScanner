package com.rezve.nidscanner.history;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rezve.nidscanner.R;
import com.rezve.nidscanner.Utils;
import com.rezve.nidscanner.models.History;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        History history = intent.getParcelableExtra("history");
        initUi(history);
    }

    private void initUi(History history) {
        TextView details = findViewById(R.id.detailsTV);
        TextView createdAt = findViewById(R.id.createdAtTV);
        details.setText(history.getDetails());
        createdAt.setText("Scanned at "+ Utils.dateToString(history.getCreatedAt(), "MMM d, yyyy h:ma"));
    }
}
