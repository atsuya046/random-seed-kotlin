package com.atsuya046.random

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class StringRandomizerTest {
    @Test
    fun generate() {
        val a = StringRandomizer.generate()
        val b = StringRandomizer.generate()

        Assertions.assertFalse { a == b }
    }
}