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

        Intent intent = getIntent();
        History history = intent.getParcelableExtra("history");
        initUi(history);
    }

    private void initUi(History history) {
        TextView name = findViewById(R.id.nameTV);
        TextView details = findViewById(R.id.detailsTV);
        TextView date = findViewById(R.id.dateTV);
        TextView createdAt = findViewById(R.id.createdAtTV);
        name.setText(history.getName());
        details.setText(history.getDetails());
        date.setText(Utils.dateToString(history.getCreatedAt(), "d MMM yyyy"));
        createdAt.setText("Scanned at "+ Utils.dateToString(history.getCreatedAt(), "h:ma"));
    }
}
