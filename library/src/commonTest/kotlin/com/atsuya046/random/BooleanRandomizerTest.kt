package com.atsuya046.random

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class BooleanRandomizerTest {

    @Test
    fun generate() {
        val booleans: List<Boolean> = (1..100).map { BooleanRandomizer.generate() }
        assertFalse { booleans.all { it } } // should generate some false
        assertTrue { booleans.any { it } } // should generate some true
    }
}