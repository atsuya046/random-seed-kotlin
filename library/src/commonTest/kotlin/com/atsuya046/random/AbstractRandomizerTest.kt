package com.atsuya046.random

import kotlin.test.Test
import kotlin.test.assertNotEquals


abstract class AbstractRandomizerTest<T : Any>(private val randomizer: Randomizer<T>) {
    @Test
    fun generate() {
        val a = randomizer.generate()
        val b = randomizer.generate()
        assertNotEquals(a, b)
    }
}