package com.example.fund.util

import android.widget.DatePicker
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 *  Created by zhaomeng on 2019/7/4
 */

object DateUtil {

    fun toDateLong(picker: DatePicker): Long {
        val calendar = Calendar.getInstance()
        calendar.set(picker.year, picker.month, picker.dayOfMonth, 16, 0, 0)
        return calendar.timeInMillis
    }

    fun toDateString(datePicker: DatePicker): String {
        return "${datePicker.year}年${datePicker.month + 1}月${datePicker.dayOfMonth}日"
    }

    fun formatData(dateNum: Long, pattern: String): String {
        val dataFormat = SimpleDateFormat(pattern)
        val date = Date(dateNum)
        return dataFormat.format(date)
    }
}