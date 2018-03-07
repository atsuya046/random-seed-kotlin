package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RandomTest {
    @Test
    @Suppress("USELESS_IS_CHECK")
    fun generate() {
        val random = Random()
        assertTrue(random.generate<String>() is String)
        assertTrue(random.generate<CharArray>() is CharArray)
        assertTrue(random.generate<Int>() is Int)
        assertTrue(random.generate<Long>() is Long)
        assertTrue(random.generate<Float>() is Float)
        assertTrue(random.generate<Double>() is Double)
    }
}