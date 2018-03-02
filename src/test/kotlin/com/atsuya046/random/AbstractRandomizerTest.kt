package com.atsuya046.random

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

abstract class AbstractRandomizerTest<out T>(private val randomizer: Randomizer<T>) {
    @Test
    fun generate() {
        val a = randomizer.generate()
        val b = randomizer.generate()
        Assertions.assertNotEquals(a, b)
    }
}