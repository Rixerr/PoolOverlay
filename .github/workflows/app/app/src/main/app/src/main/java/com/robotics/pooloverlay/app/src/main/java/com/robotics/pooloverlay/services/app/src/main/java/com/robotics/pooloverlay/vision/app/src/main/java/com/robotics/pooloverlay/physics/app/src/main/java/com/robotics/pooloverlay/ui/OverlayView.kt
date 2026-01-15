package com.robotics.pooloverlay.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.robotics.pooloverlay.physics.TrajectoryLine

class OverlayView(context: Context) : View(context) {

    private var trajectory: TrajectoryLine? = null
    
    private val mainLinePaint = Paint().apply {
        color = Color.YELLOW
        strokeWidth = 3f
        isAntiAlias = true
    }

    fun updateTrajectory(traj: TrajectoryLine?) {
        trajectory = traj
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        trajectory?.let { traj ->
            for (i in 0 until traj.points.size - 1) {
                val (x1, y1) = traj.points[i]
                val (x2, y2) = traj.points[i + 1]
                canvas.drawLine(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat(), mainLinePaint)
            }
        }
    }
}
