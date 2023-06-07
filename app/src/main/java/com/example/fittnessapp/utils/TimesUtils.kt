package com.example.fittnessapp.utils


import java.text.SimpleDateFormat
import java.util.Calendar

object TimesUtils {
    val formatter = SimpleDateFormat("mm:ss")
    fun getTime(time: Long): String {
        val cv = Calendar.getInstance()
        cv.timeInMillis = time
        return formatter.format(cv.time)
    }
}