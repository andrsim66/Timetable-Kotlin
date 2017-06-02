package com.sevenander.timetable.util

import android.util.Log
import java.util.*

/**
 * Created by andrii on 5/31/17.
 */
class Logger {
    companion object {
        private val TAG = "timetable"

        fun d() {
            d("\n")
        }

        fun d(msg: String) {
            val messageList = splitString(msg)
            for (message in messageList) {
                Log.d(TAG, message)
            }
        }

        fun i(msg: String) {
            val messageList = splitString(msg)
            for (message in messageList) {
                Log.i(TAG, message)
            }
        }

        fun i(msg: String, throwable: Throwable) {
            val messageList = splitString(msg)
            for (message in messageList) {
                Log.i(TAG, message, throwable)
            }
        }

        fun e(msg: String?) {
            val messageList = splitString(msg)
            for (message in messageList) {
                Log.e(TAG, message)
            }
        }

        fun e(msg: String?, throwable: Throwable) {
            val messageList = splitString(msg)
            for (message in messageList) {
                Log.e(TAG, message, throwable)
            }
        }

        /**
         * Divides a string into chunks of a given character size.

         * @param text      String text to be sliced
         * *
         * @param sliceSize int Number of characters
         * *
         * @return ArrayList<String>   Chunks of strings
        </String> */
        fun splitString(text: String?, sliceSize: Int): ArrayList<String> {
            val textList = ArrayList<String>()
            if (text != null) {
                var aux: String
                var left = -1
                var right = 0
                var charsLeft = text.length
                while (charsLeft != 0) {
                    left = right
                    if (charsLeft >= sliceSize) {
                        right += sliceSize
                        charsLeft -= sliceSize
                    } else {
                        right = text.length
                        charsLeft = 0
                    }
                    aux = text.substring(left, right)
                    textList.add(aux)
                }
            } else {
                textList.add("You want to Log.d (*null*)!")
            }
            return textList
        }

        /**
         * Divides a string into chunks.

         * @param text String text to be sliced
         * *
         * @return ArrayList<String>
        </String> */
        fun splitString(text: String?): ArrayList<String> {
            return splitString(text, 1000)
        }
    }
}