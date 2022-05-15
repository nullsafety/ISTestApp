package com.interactivestandard.test.data.response

data class PointsResponse(
    val points: List<CoordinatePoint>
)

data class CoordinatePoint(
    val x: Float,
    val y: Float
)
