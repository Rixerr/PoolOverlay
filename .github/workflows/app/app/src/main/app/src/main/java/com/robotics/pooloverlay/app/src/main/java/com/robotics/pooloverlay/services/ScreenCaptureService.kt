package com.robotics.pooloverlay.services

import android.app.Service
import android.app.Notification
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ScreenCaptureService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "START_OVERLAY" -> startCaptureOverlay()
            "STOP_OVERLAY" -> stopCaptureOverlay()
        }
        return START_STICKY
    }

    private fun startCaptureOverlay() {
        val notification = NotificationCompat.Builder(this, "pool_overlay")
            .setContentTitle("Pool Overlay Attivo")
            .setContentText("Tracciamento in corso...")
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .build()

        startForeground(1, notification)
    }

    private fun stopCaptureOverlay() {
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
