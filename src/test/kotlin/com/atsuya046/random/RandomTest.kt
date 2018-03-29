package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RandomTest {
    @Test
    @Suppress("USELESS_IS_CHECK")
    fun generate() {
        assertTrue(Random.generate<String>() is String)
        assertTrue(Random.generate<CharArray>() is CharArray)
        assertTrue(Random.generate<Int>() is Int)
        assertTrue(Random.generate<Long>() is Long)
        assertTrue(Random.generate<Float>() is Float)
        assertTrue(Random.generate<Double>() is Double)
    }
}