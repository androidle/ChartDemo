package com.leevinapp.chartdeom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ChartView  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private var MIN_RADIUS = 50f
    private var raduis:Float = MIN_RADIUS
    private var MAX_VOLTAGE = 250
    private var MAX_INTENSITY = 7.2F
    var UA: Float = 100F
    var UB: Float = 125F
    var UC: Float = 200F

    var IA: Float = 3.6F
    var IB: Float = 7.2F
    var IC: Float = 2.4F

    var angelA: Float = 30F
    var angelB: Float = -30F
    var angelC: Float = -10F

    private var lineUDatas = mutableListOf<LineData>()
    private var lineIDatas = mutableListOf<LineData>()


    fun calculateData() {
        lineUDatas.add(LineData(UA * raduis / MAX_VOLTAGE))
        lineUDatas.add(LineData(UB * raduis / MAX_VOLTAGE))
        lineUDatas.add(LineData(UC * raduis / MAX_VOLTAGE))

        lineIDatas.add(LineData(IA * raduis/MAX_INTENSITY,angelA))
        lineIDatas.add(LineData(IB * raduis/MAX_INTENSITY,angelB))
        lineIDatas.add(LineData(IC * raduis/MAX_INTENSITY,angelC))
    }


    private var paint:Paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
        isAntiAlias = true
    }

    private var paintU:Paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 20f
        isAntiAlias = true
    }

    private var paintI:Paint = Paint().apply {
        color = Color.RED
        strokeWidth = 6f
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        // 1.画坐标系
        drawCoordinateSystem(canvas)
        // 2.画电压A,B,C
        drawULines(canvas)
        // 3.画电流A,B,C 带角度
        drawILines(canvas)
    }

    private fun drawILines(canvas: Canvas) {
        canvas.save()
        canvas.translate(raduis, raduis)
        val rotateAngel = 120f
        for ((index, value) in lineIDatas.withIndex()) {
            canvas.rotate(value.angel)
            canvas.drawLine(0f, 0f, 0f, -value.length, paintI)
            canvas.rotate(-value.angel)
            canvas.rotate(rotateAngel)
        }
        canvas.restore()
    }

    private fun drawULines(canvas: Canvas) {
        canvas.save()
        canvas.translate(raduis, raduis)
        val rotateAngel = 120f
        for ((index, value) in lineUDatas.withIndex()) {
            canvas.drawLine(0f, 0f, 0f, -value.length, paintU)
            canvas.rotate(rotateAngel)
        }
        canvas.restore()
    }

    private fun drawCoordinateSystem(canvas: Canvas) {
        canvas.save()
        canvas.translate(raduis, raduis)
        val rotateAngel = 120f
        for (i in 0..2) {
            canvas.drawLine(0f, 0f, 0f, -raduis, paint)
            canvas.rotate(rotateAngel)
        }
        canvas.restore()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val diameter = width.coerceAtMost(height)
        raduis = (diameter / 2).coerceAtLeast(raduis.toInt()).toFloat()
    }

    data class LineData(
            val length: Float,
            val angel:Float = 0F
    )
}