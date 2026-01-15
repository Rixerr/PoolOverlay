package com.robotics.pooloverlay.physics

import kotlin.math.*

data class Vector2D(val x: Double, val y: Double) {
    operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)
    operator fun minus(other: Vector2D) = Vector2D(x - other.x, y - other.y)
    operator fun times(scalar: Double) = Vector2D(x * scalar, y * scalar)
    fun magnitude() = sqrt(x * x + y * y)
}

data class TrajectoryLine(
    val points: List<Pair<Double, Double>>,
    val bouncePoints: List<Pair<Double, Double>>,
    val targetPath: List<Pair<Double, Double>>
)

class PhysicsEngine {
    fun simulate(dummy: Any): TrajectoryLine {
        return TrajectoryLine(
            points = emptyList(),
            bouncePoints = emptyList(),
            targetPath = emptyList()
        )
    }
}
