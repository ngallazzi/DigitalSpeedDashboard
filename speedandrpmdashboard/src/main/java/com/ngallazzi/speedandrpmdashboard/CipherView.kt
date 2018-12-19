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

    private var sideLength: Float = 0f
    private var initialXOffset: Float = 0f
    private var initialYOffset: Float = 0f
    private var cathetusLength = (sideLength / 4.0f)
    private var segmentWidth = sideLength + cathetusLength * 2
    private var segmentHeight = cathetusLength * 2

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

        sideLength = Math.min(width, height) / 3.1f
        cathetusLength = sideLength / 4.0f
        translationOffset = cathetusLength * 1.3f

        segmentWidth = sideLength + cathetusLength * 2
        segmentHeight = cathetusLength * 2

        var realWidth = width - (paddingStart + paddingEnd)
        var realHeight = height - (paddingTop + paddingBottom)

        initialXOffset = (realWidth - segmentWidth) / 2
        initialYOffset = realHeight - (realHeight - ((segmentWidth + translationOffset) * 2 + segmentHeight))
        segmentZeroPath = getBasePath()
        segmentOnePath = getTranslatedPath(
            getRotatedPath(segmentZeroPath, 90.0f),
            sideLength / 2 + translationOffset,
            -sideLength / 2 - translationOffset
        )
        segmentTwoPath = getTranslatedPath(segmentOnePath, -sideLength - translationOffset * 2, 0f)
        segmentThreePath = getTranslatedPath(segmentZeroPath, 0f, -sideLength - translationOffset * 2)
        segmentFourPath = getTranslatedPath(segmentOnePath, 0f, -sideLength - translationOffset * 2)
        segmentFivePath = getTranslatedPath(segmentThreePath, 0f, -sideLength - translationOffset * 2)
        segmentSixPath = getTranslatedPath(segmentTwoPath, 0f, -sideLength - translationOffset * 2)

        drawPlaceholder(canvas!!)
        drawDigit(canvas)
    }

    public fun drawPlaceholder(canvas: Canvas) {
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

    public fun setDigit(digit: Int) {
        currentDigit = digit
        invalidate()
    }

    private fun getBasePath(): Path {

        val p0 = PointF(initialXOffset, initialYOffset - cathetusLength * 2)

        val p1 = PointF(p0.x + cathetusLength, p0.y - cathetusLength)

        val p2 = PointF(p1.x + sideLength, p1.y)

        val p3 = PointF(p0.x + sideLength + cathetusLength * 2, p2.y + cathetusLength)

        val p4 = PointF(p2.x, p3.y + cathetusLength)

        val p5 = PointF(p1.x, p0.y + cathetusLength)

        return getPath(p0, p1, p2, p3, p4, p5)
    }

    private fun getRotatedPath(sourcePath: Path, degrees: Float): Path {
        var pathToRotate = Path(sourcePath)
        val matrix = Matrix()
        val bounds = RectF()
        pathToRotate.computeBounds(bounds, true)
        matrix.postRotate(degrees, bounds.centerX(), bounds.centerY())
        pathToRotate.transform(matrix)
        return pathToRotate
    }

    private fun getTranslatedPath(sourcePath: Path, dx: Float, dy: Float): Path {
        var pathToTranslate = Path(sourcePath)
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