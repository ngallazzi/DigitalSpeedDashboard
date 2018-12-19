package com.ngallazzi.speedandrpmdashboard

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import java.util.*


/**
 * Trash
 * Created by Nicola on 12/6/2018.
 * Copyright © 2018 Zehus. All rights reserved.
 */
class SpeedAndRpmDashboard : ConstraintLayout {
    private var currentSpeed: Int = 0
    private lateinit var cvDigits: CipherView
    private lateinit var cvDozens: CipherView
    private lateinit var cvHundreds: CipherView

    init {
        inflate(context, R.layout.speed_and_rpm_dashboard_layout, this)
        cvDigits = findViewById(R.id.cvDigits)
        cvDozens = findViewById(R.id.cvDozens)
        cvHundreds = findViewById(R.id.cvHundreds)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    fun setSpeed(speed: Int) {
        currentSpeed = speed
        setDigitsToViews()
    }

    private fun getDigitsFromSpeed(): ArrayList<Int> {
        var digits = ArrayList<Int>()
        var speed = currentSpeed
        while (speed > 0) {
            digits.add(speed % 10)
            speed /= 10
        }
        return digits
    }

    private fun setDigitsToViews() {
        var digits = getDigitsFromSpeed()
        when (digits.size) {
            1 -> {
                cvDigits.setDigit(digits[0])
            }
            2 -> {
                cvDigits.setDigit(digits[0])
                cvDozens.setDigit(digits[1])
            }
            3 -> {
                cvDigits.setDigit(digits[0])
                cvDozens.setDigit(digits[1])
                cvHundreds.setDigit(digits[2])
            }
        }
    }

}