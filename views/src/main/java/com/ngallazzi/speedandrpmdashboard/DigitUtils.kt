package com.ngallazzi.speedandrpmdashboard

import java.util.*

/**
 * speedandrpmdashboard
 * Created by Nicola on 12/12/2018.
 * Copyright © 2018 Zehus. All rights reserved.
 */
class DigitUtils {
    companion object {
        fun getDigitsFromSpeed(speed: Int): LinkedList<Int> {
            val stack = LinkedList<Int>()
            var currentSpeed = speed
            while (currentSpeed > 0) {
                stack.push(currentSpeed % 10)
                currentSpeed /= 10
            }
            return stack
        }

        fun getBitmaskForDigit(digit: Int): Byte {
            return when (digit) {
                0 -> ZERO_BITMASK // 0,1,2,4,5,6
                1 -> ONE_BITMASK // 1,4
                2 -> TWO_BITMASK // 0,2,3,4,5
                3 -> THREE_BITMASK // 0,1,3,4,5
                4 -> FOUR_BITMASK // 1,3,4,6
                5 -> FIVE_BITMASK // 0,1,3,5,6
                6 -> SIX_BITMASK // 0,1,2,3,5,6
                7 -> SEVEN_BITMASK // 1,4,5
                8 -> EIGHT_BITMASK // 0,1,2,3,4,5,6
                9 -> NINE_BITMASK // 1,3,4,5,6
                else -> 0b00000000
            }
        }

        private const val ZERO_BITMASK = 0b1110111.toByte()
        private const val ONE_BITMASK = 0b0001010.toByte()
        private const val TWO_BITMASK = 0b0111100.toByte()
        private const val THREE_BITMASK = 0b0111011.toByte()
        private const val FOUR_BITMASK = 0b1011010.toByte()
        private const val FIVE_BITMASK = 0b1101010.toByte()
        private const val SIX_BITMASK = 0b1101111.toByte()
        private const val SEVEN_BITMASK = 0b0110011.toByte()
        private const val EIGHT_BITMASK = 0b1110111.toByte()
        private const val NINE_BITMASK = 0b1110111.toByte()

    }
}