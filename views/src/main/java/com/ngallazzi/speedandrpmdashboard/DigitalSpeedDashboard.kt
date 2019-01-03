package com.ngallazzi.speedandrpmdashboard

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import java.util.*


/**
 * Trash
 * Created by Nicola on 12/6/2018.
 * Copyright © 2018 Zehus. All rights reserved.
 */
class DigitalSpeedDashboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var currentSpeed: Int = 0
    private var cvDigits: CipherView
    private var cvDozens: CipherView
    private var cvHundreds: CipherView
    private var cpvProgress: CircleProgressView
    private var attributes: TypedArray
    private var mTachometerOnColor: Int
    private var mTachometerOffColor: Int
    private var mRingBaseColor: Int
    private var mIndicatorColor: Int

    init {
        inflate(context, R.layout.digital_speed_dashboard_layout, this)
        cvDigits = findViewById(R.id.cvDigits)
        cvDozens = findViewById(R.id.cvDozens)
        cvHundreds = findViewById(R.id.cvHundreds)
        cpvProgress = findViewById(R.id.cpvProgress)

        attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DigitalSpeedDashboard,
            0, 0
        )

        mTachometerOnColor = attributes.getColor(
            R.styleable.DigitalSpeedDashboard_speedColor,
            ContextCompat.getColor(context, R.color.colorTachometerOn)
        )

        mTachometerOffColor = attributes.getColor(
            R.styleable.DigitalSpeedDashboard_idleColor,
            ContextCompat.getColor(context, R.color.colorTachometerOff)
        )

        mRingBaseColor = attributes.getColor(
            R.styleable.DigitalSpeedDashboard_ringBaseColor,
            ContextCompat.getColor(context, R.color.colorTachometerOn)
        )

        mIndicatorColor = attributes.getColor(
            R.styleable.DigitalSpeedDashboard_circleIndicatorColor,
            ContextCompat.getColor(context, R.color.circleIndicatorColor)
        )

        cvHundreds.setSpeedColor(mTachometerOnColor)
        cvDozens.setSpeedColor(mTachometerOnColor)
        cvDigits.setSpeedColor(mTachometerOnColor)

        cvHundreds.setIdleColor(mTachometerOffColor)
        cvDozens.setIdleColor(mTachometerOffColor)
        cvDigits.setIdleColor(mTachometerOffColor)

        cpvProgress.setRingBaseColor(mRingBaseColor)
        cpvProgress.setProgressColors(mRingBaseColor)
        cpvProgress.setCircleIndicatorColor(mIndicatorColor)

    }

    fun setSpeed(speed: Int) {
        currentSpeed = speed
        setDigitsToViews()
        cpvProgress.setProgress(speed)
    }

    fun setMaxSpeed(speed: Int) {
        cpvProgress.setMaxProgress(speed)
    }

    private fun getDigitsFromSpeed(): ArrayList<Int> {
        val digits = ArrayList<Int>()
        var speed = currentSpeed
        while (speed > 0) {
            digits.add(speed % 10)
            speed /= 10
        }
        return digits
    }

    private fun setDigitsToViews() {
        val digits = getDigitsFromSpeed()
        when (digits.size) {
            1 -> {
                cvDigits.setDigit(digits[0])
                cvDozens.setDigit(0)
                cvHundreds.setDigit(0)
            }
            2 -> {
                cvDigits.setDigit(digits[0])
                cvDozens.setDigit(digits[1])
                cvHundreds.setDigit(0)
            }
            3 -> {
                cvDigits.setDigit(digits[0])
                cvDozens.setDigit(digits[1])
                cvHundreds.setDigit(digits[2])
            }
        }
    }
}