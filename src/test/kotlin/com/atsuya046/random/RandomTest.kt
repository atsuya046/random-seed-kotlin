package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RandomTest {
    @Test
    fun generate() {
        val randomValue = Random.generate<Int>()
        assertTrue { Int.MIN_VALUE <= randomValue && randomValue <= Int.MAX_VALUE }
    }
}