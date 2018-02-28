package com.atsuya046.random

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class RandomizerTest {

    abstract class RandomizerTest<out T>(val randomizer: Randomizer<T>) {
        @Test
        fun generate() {
            val a = randomizer.generate()
            val b = randomizer.generate()
            Assertions.assertNotEquals(a, b)
        }
    }

    @Nested
    inner class LongRandomizerTest : RandomizerTest<Long>(LongRandomizer)

    @Nested
    inner class IntRandomizerTest : RandomizerTest<Int>(IntRandomizer)

    @Nested
    inner class FloatRandomizerTest : RandomizerTest<Float>(FloatRandomizer)

    @Nested
    inner class DoubleRandomizerTest : RandomizerTest<Double>(DoubleRandomizer)
}