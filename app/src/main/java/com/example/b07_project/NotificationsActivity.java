package com.example.b07_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<NotificationItem> notificationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        listView = findViewById(R.id.listViewNotifications);

        // Get the list of notifications from NotificationHelper
        List<NotificationItem> notificationList = NotificationHelper.getNotificationList();

        // Create an ArrayAdapter to display notifications in the ListView
        notificationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notificationList);
        listView.setAdapter(notificationAdapter);

        //button for go back
        Button goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}