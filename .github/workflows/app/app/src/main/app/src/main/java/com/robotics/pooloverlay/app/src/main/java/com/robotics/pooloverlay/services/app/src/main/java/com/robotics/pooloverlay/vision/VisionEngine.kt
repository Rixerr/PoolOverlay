package com.robotics.pooloverlay.vision

import android.graphics.Point
import kotlin.math.*

data class PoolDetectionResult(
    val whiteBallPos: Point,
    val targetBallPos: Point,
    val cueAngle: Double,
    val tableCorners: List<Point>,
    val confidenceScore: Double
)

class VisionEngine {

    fun detectPool(dummy: Any): PoolDetectionResult {
        return PoolDetectionResult(
            whiteBallPos = Point(100, 100),
            targetBallPos = Point(200, 200),
            cueAngle = 0.0,
            tableCorners = emptyList(),
            confidenceScore = 0.85
        )
    }
}
