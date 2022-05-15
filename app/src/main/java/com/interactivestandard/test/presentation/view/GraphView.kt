package com.interactivestandard.test.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs

class GraphView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private val dataSet = mutableListOf<DataPoint>()

    private var xMin = 0F
    private var xMax = 0F
    private var xSum = 0F

    private var yMin = 0F
    private var yMax = 0F
    private var ySum = 0F

    private val dataPointPaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val dataPointFillPaint = Paint().apply {
        color = Color.WHITE
    }

    private val dataPointLinePaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        dataSet.forEachIndexed { index, currentDataPoint ->
            val realX = currentDataPoint.xVal.toRealX()
            val realY = currentDataPoint.yVal.toRealY()

            if (index < dataSet.size - 1) {
                val nextDataPoint = dataSet[index + 1]
                val startX = currentDataPoint.xVal.toRealX()
                val startY = currentDataPoint.yVal.toRealY()
                val endX = nextDataPoint.xVal.toRealX()
                val endY = nextDataPoint.yVal.toRealY()
                canvas.drawLine(startX, startY, endX, endY, dataPointLinePaint)
            }

            canvas.drawCircle(realX, realY, 5f, dataPointFillPaint)
            canvas.drawCircle(realX, realY, 5f, dataPointPaint)
        }
    }

    fun setData(newDataSet: List<DataPoint>) {
        xMin = newDataSet.minByOrNull { it.xVal }?.xVal ?: 0F
        xMax = newDataSet.maxByOrNull { it.xVal }?.xVal ?: 0F

        xSum = if (xMin < 0 && xMax > 0) {
            abs(xMin).plus(xMax)
        } else if (xMin < 0 && xMax < 0) {
            abs(xMin).minus(abs(xMax))
        } else {
            xMin.plus(xMax)
        }

        yMin = newDataSet.minByOrNull { it.yVal }?.yVal ?: 0F
        yMax = newDataSet.maxByOrNull { it.yVal }?.yVal ?: 0F

        ySum = if (yMin < 0 && yMax > 0) {
            abs(yMin).plus(yMax)
        } else if (yMin < 0 && yMax < 0) {
            abs(yMin).minus(abs(yMax))
        } else {
            yMin.plus(yMax)
        }

        dataSet.clear()
        dataSet.addAll(newDataSet)
        invalidate()
    }

    private fun Float.toRealX(): Float {
        val x = if (this < 0) {
            abs(xMin - this)
        } else {
            this - xMin
        }
        return width / xSum * x
    }
    private fun Float.toRealY(): Float {
        val y =
            if (this < 0) {
                abs(yMin - this)
            } else {
                this - yMin
            }
        return height / ySum * y
    }
}

data class DataPoint(
    val xVal: Float,
    val yVal: Float
)