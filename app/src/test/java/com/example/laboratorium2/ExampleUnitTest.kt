package com.example.laboratorium2

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testStandardowegoFormatowania() {
        val wynik = przeliczIFormatuj(2.0, 100.0)
        assertEquals("200.0000", wynik)
    }

    @Test
    fun testNotacjiWykladniczejDlaDuzychLiczb() {
        val wynik = przeliczIFormatuj(20.0, 100.0)
        assertEquals("2.0000e+03", wynik)
    }

    @Test
    fun testNotacjiWykladniczejDlaMalychLiczb() {
        val wynik = przeliczIFormatuj(0.5, 0.001)
        assertEquals("5.0000e-04", wynik)
    }
}