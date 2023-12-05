package com.example.b07_project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;


import java.util.ArrayList;
import java.util.List;

public class NotificationHelper {

    private static final String EVENT_CHANNEL_ID = "EventChannelId";
    private static final String ANNOUNCEMENT_CHANNEL_ID = "AnnouncementChannelId";

    // List to store notifications
    private static List<NotificationItem> notificationList = new ArrayList<>();
    private static int notificationId = 0;

    public static void showEventNotification(Context context, String eventName, String eventDetails) {

        // Create a NotificationItem and add it to the list
        NotificationItem eventNotification = new NotificationItem(eventName, eventDetails);
        notificationList.add(eventNotification);

        // Notify the activity that a new notification is available
        notifyDataChanged(context, eventName, eventDetails);
    }

    public static void showAnnouncementNotification(Context context, String title, String announcement) {

        // Create a NotificationItem and add it to the list
        NotificationItem announcementNotification = new NotificationItem(title, announcement);
        notificationList.add(announcementNotification);

        // Notify the activity that a new notification is available
        notifyDataChanged(context, title, announcement);
    }

    public static List<NotificationItem> getNotificationList() {
        return notificationList;
    }

    private static void notifyDataChanged(Context context, String title, String content) {
        // Build and show a new notification using the NotificationCompat.Builder
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ANNOUNCEMENT_CHANNEL_ID, "Announcement Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ANNOUNCEMENT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(notificationId++, builder.build());
    }
}