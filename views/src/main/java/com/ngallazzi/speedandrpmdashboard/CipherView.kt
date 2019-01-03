package com.ngallazzi.speedandrpmdashboard

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View

/**
 * speedandrpmdashboard
 * Created by Nicola on 12/12/2018.
 * Copyright © 2018 Zehus. All rights reserved.
 */
class CipherView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var sideWidth: Float = 0f
    private var initialXOffset: Float = 0f
    private var initialYOffset: Float = 0f
    private var cathetusWidth = (sideWidth / 4.0f)
    private var segmentWidth = sideWidth + cathetusWidth * 2
    private var segmentHeight = cathetusWidth * 2

    private var translationOffset = 0.0f
    private var placeholderPaint: Paint =
        Paint().apply { color = ContextCompat.getColor(context, R.color.colorTachometerOff) }
    private var digitPaint: Paint = Paint().apply { color = ContextCompat.getColor(context, R.color.colorTachometerOn) }
    private var currentDigit = 0

    private lateinit var segmentZeroPath: Path
    private lateinit var segmentOnePath: Path
    private lateinit var segmentTwoPath: Path
    private lateinit var segmentThreePath: Path
    private lateinit var segmentFourPath: Path
    private lateinit var segmentFivePath: Path
    private lateinit var segmentSixPath: Path

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        cathetusWidth = sideWidth / 4.0f
        translationOffset = cathetusWidth * 1.3f

        segmentWidth = sideWidth + cathetusWidth * 2
        segmentHeight = cathetusWidth * 2

        val realWidth = width - (paddingStart + paddingEnd)
        val realHeight = height - (paddingTop + paddingBottom)

        initialXOffset = (realWidth - segmentWidth) / 2
        initialYOffset = realHeight - (realHeight - ((segmentWidth + translationOffset) * 2 + segmentHeight))

        segmentZeroPath = getBasePath()
        segmentOnePath = getTranslatedPath(
            getRotatedPath(segmentZeroPath, 90.0f),
            sideWidth / 2 + translationOffset,
            -sideWidth / 2 - translationOffset
        )
        segmentTwoPath = getTranslatedPath(segmentOnePath, -sideWidth - translationOffset * 2, 0f)
        segmentThreePath = getTranslatedPath(segmentZeroPath, 0f, -sideWidth - translationOffset * 2)
        segmentFourPath = getTranslatedPath(segmentOnePath, 0f, -sideWidth - translationOffset * 2)
        segmentFivePath = getTranslatedPath(segmentThreePath, 0f, -sideWidth - translationOffset * 2)
        segmentSixPath = getTranslatedPath(segmentTwoPath, 0f, -sideWidth - translationOffset * 2)

        drawPlaceholder(canvas!!)
        drawDigit(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val DEFAULT_WIDTH = 96
        val DEFAULT_HEIGHT = (DEFAULT_WIDTH * 1.6).toInt()
        val MAX_WIDTH = DEFAULT_WIDTH * 2

        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val specifiedWidthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val specifiedHeightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        var width = 0
        var height = 0

        when (widthMode) {
            MeasureSpec.EXACTLY -> {
                width = specifiedWidthSize
            }
            MeasureSpec.AT_MOST -> {
                //Can't be bigger than...
                width = Math.min(MAX_WIDTH, specifiedWidthSize)
            }
            MeasureSpec.UNSPECIFIED -> {
                width = DEFAULT_WIDTH
            }
        }

        when (heightMode) {
            MeasureSpec.EXACTLY -> {
                height = specifiedHeightSize
            }
            MeasureSpec.AT_MOST -> {
                //Can't be bigger than...
                height = (width * 1.31).toInt()
            }
            MeasureSpec.UNSPECIFIED -> {
                height = DEFAULT_HEIGHT
            }
        }

        sideWidth = Math.min(width, height) / 3.1f

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }

    private fun drawPlaceholder(canvas: Canvas) {
        for (index in 0..9) {
            drawSegment(index, canvas, placeholderPaint)
        }
    }

    private fun drawDigit(canvas: Canvas) {
        when (currentDigit) {
            0 -> {
                for (index in ZERO_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            1 -> {
                for (index in ONE_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            2 -> {
                for (index in TWO_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            3 -> {
                for (index in THREE_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            4 -> {
                for (index in FOUR_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            5 -> {
                for (index in FIVE_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            6 -> {
                for (index in SIX_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            7 -> {
                for (index in SEVEN_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            8 -> {
                for (index in EIGHT_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
            9 -> {
                for (index in NINE_DIGIT_PATTERN) {
                    drawSegment(index, canvas, digitPaint)
                }
            }
        }
    }

    private fun drawSegment(index: Int, canvas: Canvas, paint: Paint) {
        when (index) {
            0 -> {
                canvas.drawPath(segmentZeroPath, paint)
            }
            1 -> {
                canvas.drawPath(segmentOnePath, paint)
            }
            2 -> {
                canvas.drawPath(segmentTwoPath, paint)
            }
            3 -> {
                canvas.drawPath(segmentThreePath, paint)
            }
            4 -> {
                canvas.drawPath(segmentFourPath, paint)
            }
            5 -> {
                canvas.drawPath(segmentFivePath, paint)
            }
            6 -> {
                canvas.drawPath(segmentSixPath, paint)
            }
        }
    }

    fun setDigit(digit: Int) {
        currentDigit = digit
        invalidate()
    }

    fun setIdleColor(color: Int) {
        placeholderPaint.color = color
    }

    fun setSpeedColor(color: Int) {
        digitPaint.color = color
    }

    private fun getBasePath(): Path {

        val p0 = PointF(initialXOffset, initialYOffset - cathetusWidth * 2)

        val p1 = PointF(p0.x + cathetusWidth, p0.y - cathetusWidth)

        val p2 = PointF(p1.x + sideWidth, p1.y)

        val p3 = PointF(p0.x + sideWidth + cathetusWidth * 2, p2.y + cathetusWidth)

        val p4 = PointF(p2.x, p3.y + cathetusWidth)

        val p5 = PointF(p1.x, p0.y + cathetusWidth)

        return getPath(p0, p1, p2, p3, p4, p5)
    }

    private fun getRotatedPath(sourcePath: Path, degrees: Float): Path {
        val pathToRotate = Path(sourcePath)
        val matrix = Matrix()
        val bounds = RectF()
        pathToRotate.computeBounds(bounds, true)
        matrix.postRotate(degrees, bounds.centerX(), bounds.centerY())
        pathToRotate.transform(matrix)
        return pathToRotate
    }

    private fun getTranslatedPath(sourcePath: Path, dx: Float, dy: Float): Path {
        val pathToTranslate = Path(sourcePath)
        val translateMatrix = Matrix()
        translateMatrix.setTranslate(dx, dy)
        pathToTranslate.transform(translateMatrix)
        return pathToTranslate
    }

    private fun getPath(p0: PointF, p1: PointF, p2: PointF, p3: PointF, p4: PointF, p5: PointF): Path {
        val path = Path()
        // P0 - P1
        path.moveTo(p0.x, p0.y)
        path.lineTo(p1.x, p1.y)
        // P1 - P2
        path.lineTo(p2.x, p2.y)
        // P2 - P3
        path.lineTo(p3.x, p3.y)
        // P3 - P4
        path.lineTo(p4.x, p4.y)
        // P4 - P5
        path.lineTo(p5.x, p5.y)
        // P5 - P0
        path.lineTo(p0.x, p0.y)
        path.close()
        return path
    }

    companion object {
        val ZERO_DIGIT_PATTERN = intArrayOf(0, 1, 2, 4, 5, 6)
        val ONE_DIGIT_PATTERN = intArrayOf(1, 4)
        val TWO_DIGIT_PATTERN = intArrayOf(0, 2, 3, 4, 5)
        val THREE_DIGIT_PATTERN = intArrayOf(0, 1, 3, 4, 5)
        val FOUR_DIGIT_PATTERN = intArrayOf(1, 3, 4, 6)
        val FIVE_DIGIT_PATTERN = intArrayOf(0, 1, 3, 5, 6)
        val SIX_DIGIT_PATTERN = intArrayOf(0, 1, 2, 3, 5, 6)
        val SEVEN_DIGIT_PATTERN = intArrayOf(1, 4, 5)
        val EIGHT_DIGIT_PATTERN = intArrayOf(0, 1, 2, 3, 4, 5, 6)
        val NINE_DIGIT_PATTERN = intArrayOf(0, 1, 3, 4, 5, 6)
    }
}