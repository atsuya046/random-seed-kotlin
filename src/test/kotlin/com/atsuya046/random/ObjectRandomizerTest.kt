package com.atsuya046.random

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ObjectRandomizerTest {
    @Test
    @Suppress("USELESS_IS_CHECK")
    fun generate() {
        val objectRandomizer = ObjectRandomizer(Random)
        assert(objectRandomizer.generate(TestData1::class) is TestData1)

        assert(objectRandomizer.generate(TestData2::class) is TestData2)
        assert(objectRandomizer.generate(TestData2::class).i is Int)
        assert(objectRandomizer.generate(TestData2::class).s is String)
    }

    @Test
    fun generateWithSuperClass() {
        val objectRandomizer = ObjectRandomizer(Random)
        val result = objectRandomizer.generate(TestBase::class)
        assert(result is TestInherited1 || result is TestInherited2)
    }

    @Test
    fun generateNullableField() {
        val objectRandomizer = ObjectRandomizer(Random)
        val randomizedValues = (1..100).map { objectRandomizer.generate(TestNullableField::class) }
        assertTrue(randomizedValues.any { it.nullable == null })
        assertTrue(randomizedValues.any { it.nullable != null })
    }

    private class TestData1
    private class TestData2(val i: Int, val s: String)

    private abstract class TestBase(val s: String)
    private class TestInherited1(s: String) : TestBase(s)
    private class TestInherited2(val i: Int, s: String) : TestBase(s)

    private class TestNullableField(val nullable: String?)
}