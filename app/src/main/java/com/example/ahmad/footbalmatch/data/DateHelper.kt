package com.example.ahmad.footbalmatch.data

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    companion object {

        const val DATE_FORMAT_FULL_DATE = "E, dd MMM yyyy"
        const val DATE_FORMAT_YEAR_FIRST = "yyyy-MM-dd"

        private fun stringToDate(stringDate: String, formatInput: String): Date {
            return if (stringDate.isNullOrEmpty()) {
                Calendar.getInstance().time
            } else {
                SimpleDateFormat(formatInput, Locale.getDefault()).parse(stringDate)
            }
        }

        private fun dateToString(date: Date, formatOutput: String): String {
            return SimpleDateFormat(formatOutput, Locale.getDefault()).format(date)
        }

        fun reformatStringDate(stringDate: String, formatInput: String, formatOutput: String): String {
            return dateToString(stringToDate(stringDate, formatInput), formatOutput)
        }
    }
}