package com.atsuya046.random

import org.junit.jupiter.api.Test

internal class ObjectRandomizerTest {
    @Test
    @Suppress("USELESS_IS_CHECK")
    fun generate() {
        val objectRandomizer = ObjectRandomizer(Random())
        assert(objectRandomizer.generate(TestData1::class) is TestData1)

        assert(objectRandomizer.generate(TestData2::class) is TestData2)
        assert(objectRandomizer.generate(TestData2::class).i is Int)
        assert(objectRandomizer.generate(TestData2::class).s is String)
    }

    private class TestData1
    private class TestData2(val i: Int, val s: String)
}