package com.example.b07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07_project.PostAnnouncementsActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AnnouncementsListActivity extends AppCompatActivity {

    private ListView announcementsListView;
    private ArrayList<String> announcementsList;
    private ArrayAdapter<String> announcementsAdapter;
    DatabaseReference announcementsReference = FirebaseDatabase.getInstance().getReference().child("announcement");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_list);

        announcementsListView = findViewById(R.id.announcementsListView);
        Button backToPostButton = findViewById(R.id.backToPostButton);

        announcementsList = new ArrayList<>();
        announcementsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, announcementsList);
        announcementsListView.setAdapter(announcementsAdapter);

        backToPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPostAnnouncement();
            }
        });

        // Receive posted announcement
        Intent intent = getIntent();
        if (intent != null) {
            String announcement = intent.getStringExtra("announcement");

            updateAnnouncementsList(announcement);
        }
    }

    public void updateAnnouncementsList(String announcement) {
        // Ensure announcementsList is not null
        if (announcementsList != null) {
            announcementsList.add(announcement);
            announcementsAdapter.notifyDataSetChanged();
        }
    }

    public void backToPostAnnouncement() {
        Intent intent = new Intent(this, PostAnnouncementsActivity.class);
        startActivity(intent);
    }
}
