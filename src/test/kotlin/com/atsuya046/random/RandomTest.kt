package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertEquals
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

    @Test
    fun generateOrNull() {
        val randomizedValues = (1..100).map { Random.generateNullable<String>() }
        assertTrue(randomizedValues.any { it == null })
        assertTrue(randomizedValues.any { it != null })
    }

    @Test
    fun generateInlineClass() {
        val random = Random.newInstance()
                .apply {
                    register { TestInline(Random.generate()) }
                }
        val generated = random.generate<TestInline>()
        assertTrue(generated is TestInline)
    }

    @Test
    fun customRandomizer() {
        (1..5).forEach {
            val random = Random.newInstance().apply { register(FixRandomizer(it)) }
            assertEquals(random.generate(), it)
        }
    }

    @Test
    fun easyRegisterCustomRandomizer() {
        (1..5).forEach {
            val random = Random.newInstance().apply {
                register { it }
                register { "$it" }
            }
            assertEquals(random.generate(), it)
            assertEquals(random.generate<String>(), "$it")
        }
    }

    class FixRandomizer<T : Any>(val fixed: T) : Randomizer<T>() {
        override fun generate(): T = fixed
    }
}

inline class TestInline(val value: Int)