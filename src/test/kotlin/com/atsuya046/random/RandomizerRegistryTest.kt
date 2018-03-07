package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RandomizerRegistryTest {

    @Test
    @Suppress("USELESS_IS_CHECK")
    fun choose() {
        val registry = RandomizerRegistry()

        assertTrue(registry.choose<String>().first().generate() is String)
        assertTrue(registry.choose<CharArray>().first().generate() is CharArray)
        assertTrue(registry.choose<Int>().first().generate() is Int)
        assertTrue(registry.choose<Long>().first().generate() is Long)
        assertTrue(registry.choose<Float>().first().generate() is Float)
        assertTrue(registry.choose<Double>().first().generate() is Double)
    }
}