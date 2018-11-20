package com.example.ahmad.footbalmatch

import com.example.ahmad.footbalmatch.data.DateHelper
import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilsTest {
    @Test
    fun testDateUtils() {
        assertEquals("Mon, 05 Nov 2018", DateHelper.reformatStringDate("2018-11-05", "yyyy-MM-dd", "E, dd MMM yyyy"))
    }
}