package com.ngallazzi.speedandrpmdashboard

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View


/**
 * DigitalSpeedDashboard
 * Created by Nicola on 12/21/2018.
 * Copyright © 2018 Zehus. All rights reserved.ù

 */


class CircleProgressView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var ringBackgroundPaint: Paint = Paint()
    private var ringProgressPaint: Paint = Paint()
    private var circleIndicatorPaint: Paint = Paint()
    private var centerX: Float = 0.0f
    private var centerY: Float = 0.0f
    private var radius: Float = 3.0f
    private var maxProgress = 360.0f
    private var mProgress = 0.0f
    private var mProgressAngle = 0.0f

    init {
        ringBackgroundPaint.color = ContextCompat.getColor(context, R.color.colorTachometerOff)
        ringBackgroundPaint.style = Paint.Style.STROKE
        ringBackgroundPaint.strokeWidth = BASE_STROKE_WIDTH

        ringProgressPaint.color = ContextCompat.getColor(context, R.color.colorTachometerOn)
        ringProgressPaint.style = Paint.Style.STROKE
        ringProgressPaint.strokeWidth = BASE_STROKE_WIDTH / 1.8f

        circleIndicatorPaint.color = ContextCompat.getColor(context, R.color.circleIndicatorColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackgroundCircle(canvas)
        drawProgressCircle(canvas)
        drawCircleIndicator(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width / 2.0f
        centerY = height / 2.0f
        radius = (width - ringBackgroundPaint.strokeWidth - 10) / 2.0f
        var colors = intArrayOf(
            ContextCompat.getColor(context, R.color.colorTachometerOff),
            ContextCompat.getColor(context, R.color.colorTachometerOn_10),
            ContextCompat.getColor(context, R.color.colorTachometerOn_20),
            ContextCompat.getColor(context, R.color.colorTachometerOn_30),
            ContextCompat.getColor(context, R.color.colorTachometerOn_40),
            ContextCompat.getColor(context, R.color.colorTachometerOn_50),
            ContextCompat.getColor(context, R.color.colorTachometerOn_60),
            ContextCompat.getColor(context, R.color.colorTachometerOn_70),
            ContextCompat.getColor(context, R.color.colorTachometerOn_80),
            ContextCompat.getColor(context, R.color.colorTachometerOn_90),
            ContextCompat.getColor(context, R.color.colorTachometerOn)
        )
        var positions = floatArrayOf(0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f)
        ringProgressPaint.shader = SweepGradient(centerX, centerY, colors, positions)
    }

    private fun drawBackgroundCircle(canvas: Canvas?) {
        canvas?.drawCircle(
            centerX,
            centerY,
            radius,
            ringBackgroundPaint
        )
    }

    private fun drawCircleIndicator(canvas: Canvas?) {
        var xCoordinate = centerX + radius * Math.cos(Math.toRadians(mProgressAngle.toDouble()))
        var yCoordinate = centerY + radius * Math.sin(Math.toRadians(mProgressAngle.toDouble()))

        canvas?.drawCircle(
            xCoordinate.toFloat(),
            yCoordinate.toFloat(), BASE_STROKE_WIDTH / 1.75f,
            circleIndicatorPaint
        )
    }

    public fun setMaxProgress(progress: Int) {
        maxProgress = progress.toFloat()
    }

    public fun setProgress(progress: Int) {
        mProgress = progress.toFloat()
        updateProgressAngle()
    }

    private fun updateProgressAngle() {
        mProgressAngle = (mProgress / maxProgress * 1.00f) * 360f
        invalidate()
    }

    private fun drawProgressCircle(canvas: Canvas?) {
        val oval = RectF()
        oval.set(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )

        canvas?.drawArc(oval, 0f, mProgressAngle, false, ringProgressPaint)
    }

    companion object {
        const val BASE_STROKE_WIDTH = 55f
        val TAG = CircleProgressView::class.simpleName
    }
}

