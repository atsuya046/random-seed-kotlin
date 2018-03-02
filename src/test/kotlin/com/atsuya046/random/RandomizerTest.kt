package com.atsuya046.random

import org.junit.jupiter.api.Nested

internal class RandomizerTest {
    @Nested
    inner class LongRandomizerTest : AbstractRandomizerTest<Long>(LongRandomizer)

    @Nested
    inner class IntRandomizerTest : AbstractRandomizerTest<Int>(IntRandomizer)

    @Nested
    inner class FloatRandomizerTest : AbstractRandomizerTest<Float>(FloatRandomizer)

    @Nested
    inner class DoubleRandomizerTest : AbstractRandomizerTest<Double>(DoubleRandomizer)

    @Nested
    inner class StringRandomizerTest: AbstractRandomizerTest<String>(StringRandomizer)

    @Nested
    inner class CharArrayRandomizerTest: AbstractRandomizerTest<CharArray>(CharArrayRandomizer)
}