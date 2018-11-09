package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BooleanRandomizerTest {

    @Test
    fun generate() {
        val booleans: List<Boolean> = (1..100).map { BooleanRandomizer.generate() }
        assertFalse { booleans.all { it } } // should generate some false
        assertTrue { booleans.any { it } } // should generate some true
    }
}