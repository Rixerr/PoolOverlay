package com.robotics.pooloverlay

import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ToggleButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mediaProjectionManager: MediaProjectionManager
    private lateinit var toggleButton: ToggleButton
    private var isOverlayActive = false
    private val PERMISSION_REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaProjectionManager = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        toggleButton = findViewById(R.id.toggleOverlay)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startOverlay()
            } else {
                stopOverlay()
            }
        }
    }

    private fun startOverlay() {
        val intent = mediaProjectionManager.createScreenCaptureIntent()
        startActivityForResult(intent, PERMISSION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PERMISSION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val intent = Intent(this, ScreenCaptureService::class.java).apply {
                action = "START_OVERLAY"
                putExtra("mediaProjection", data)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }

            isOverlayActive = true
            Toast.makeText(this, "Overlay avviato!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopOverlay() {
        val intent = Intent(this, ScreenCaptureService::class.java).apply {
            action = "STOP_OVERLAY"
        }
        stopService(intent)
        isOverlayActive = false
        Toast.makeText(this, "Overlay fermato!", Toast.LENGTH_SHORT).show()
    }
}
