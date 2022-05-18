package com.ntech.androidtests

import org.junit.Assert.*

import org.junit.Test

class TaxTest {

    @Test
    fun calculateTaxTest() {
        val tax = Tax()
        val netText = tax.calculateTax(100.0, 0.1)

    }

    @Test
    fun calculateIncomeTest() {

    }
}