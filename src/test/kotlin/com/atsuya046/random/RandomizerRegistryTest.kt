package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RandomizerRegistryTest {

    @Test
    @Suppress("USELESS_IS_CHECK")
    fun choose() {
        val registry = RandomizerRegistry()

        assertTrue(registry.choose<String>().generate() is String)
        assertTrue(registry.choose<CharArray>().generate() is CharArray)
        assertTrue(registry.choose<Int>().generate() is Int)
        assertTrue(registry.choose<Long>().generate() is Long)
        assertTrue(registry.choose<Float>().generate() is Float)
        assertTrue(registry.choose<Double>().generate() is Double)
    }
}