package com.atsuya046.random

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class CharArrayRandomizerTest {
    @Test
    fun generate() {
        val a = CharArrayRandomizer.generate()
        val b = CharArrayRandomizer.generate()

        Assertions.assertFalse { a == b }
    }
}