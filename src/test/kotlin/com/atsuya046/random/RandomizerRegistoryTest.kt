package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RandomizerRegistoryTest {

    @Test
    @Suppress("USELESS_IS_CHECK")
    fun choose() {
        val registory = RandomizerRegistory()

        assertTrue(registory.choose<String>().generate() is String)
        assertTrue(registory.choose<CharArray>().generate() is CharArray)
        assertTrue(registory.choose<Int>().generate() is Int)
        assertTrue(registory.choose<Long>().generate() is Long)
        assertTrue(registory.choose<Float>().generate() is Float)
        assertTrue(registory.choose<Double>().generate() is Double)
    }
}