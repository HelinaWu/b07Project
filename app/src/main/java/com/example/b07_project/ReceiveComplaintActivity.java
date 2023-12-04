package com.example.b07_project;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseListAdapter;

public class ReceiveComplaintActivity extends AppCompatActivity {
    private DatabaseReference complaintsReference;
    private FirebaseListAdapter<ComplaintModel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_complaint);

        Button backToMainButton = findViewById(R.id.backToPostButton);
        complaintsReference = FirebaseDatabase.getInstance().getReference().child("complaints");

        ListView complaintsListView = findViewById(R.id.complaintsListView);

        FirebaseListOptions<ComplaintModel> options = new FirebaseListOptions.Builder<ComplaintModel>()
                .setLayout(android.R.layout.simple_list_item_1)
                .setQuery(complaintsReference, ComplaintModel.class)
                .build();

        adapter = new FirebaseListAdapter<ComplaintModel>(options) {
            @Override
            protected void populateView(View v, ComplaintModel model, int position) {
                TextView textView = v.findViewById(android.R.id.text1);
                textView.setText(model.getComment());
            }
        };

        complaintsListView.setAdapter(adapter);

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Start listening for changes when the activity is in the foreground
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Stop listening for changes when the activity is in the background
        adapter.stopListening();
    }

    public void backToMain() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
