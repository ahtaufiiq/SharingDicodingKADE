package com.example.ahmad.footbalmatch.model

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    companion object {

        val DATE_FORMAT_FULL_DATE = "E, dd MMM yyyy"
        val DATE_FORMAT_YEAR_FIRST = "yyyy-MM-dd"

        fun stringToDate(stringDate: String, formatInput: String): Date {
            return if (stringDate.isNullOrEmpty()) {
                Calendar.getInstance().time
            } else {
                SimpleDateFormat(formatInput, Locale.getDefault()).parse(stringDate)
            }
        }

        fun dateToString(date: Date, formatOutput: String): String {
            return SimpleDateFormat(formatOutput, Locale.getDefault()).format(date)
        }

        fun reformatStringDate(stringDate: String, formatInput: String, formatOutput: String): String {
            return dateToString(stringToDate(stringDate, formatInput), formatOutput)
        }
    }
}