package com.reyaz.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"
    val notificationId = System.currentTimeMillis().toInt()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.notificationBtn)

        createNotificationChannel()

        val notificationContent =
            NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("This is Title")
                .setContentText("This is the body of the notification which contain details of the notification")
                .setSmallIcon(R.drawable.twotone_123_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build()

        val notificationManager = NotificationManagerCompat.from(this)

        btn.setOnClickListener {
//        Toast.makeText(this, "fxn is called", Toast.LENGTH_SHORT).show()

            notificationManager.notify(
                notificationId, notificationContent
            )
        }
    }

    fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                // function to execute after receiving notification
                description = "This is My Notification channel"
                enableLights(true)
                lightColor = Color.BLUE

            }
            // register the channel to the system
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}