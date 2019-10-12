package com.atsuya046.random

import kotlin.test.Test
import kotlin.test.assertTrue

internal class ObjectRandomizerTest {
    @Test
    @Suppress("USELESS_IS_CHECK")
    fun generate() {
        val objectRandomizer = ObjectRandomizer(Random)
        assertTrue(objectRandomizer.generate(TestData1::class) is TestData1)

        assertTrue(objectRandomizer.generate(TestData2::class) is TestData2)
        assertTrue(objectRandomizer.generate(TestData2::class).i is Int)
        assertTrue(objectRandomizer.generate(TestData2::class).s is String)
    }

    @Test
    fun generateSealedSubclass() {
        val objectRandomizer = ObjectRandomizer(Random)
        val randomizedValues = (1..100).map { objectRandomizer.generate(TestSealed::class) }
        assertTrue(randomizedValues.any { it is TestSealed.A })
        assertTrue(randomizedValues.any { it is TestSealed.B })
        assertTrue(randomizedValues.any { it is TestSealed.C })
    }

    @Test
    fun generateWithSuperClass() {
        val objectRandomizer = ObjectRandomizer(Random)
        val result = objectRandomizer.generate(TestBase::class)
        assertTrue(result is TestInherited1 || result is TestInherited2)
    }

    @Test
    fun generateNullableField() {
        val objectRandomizer = ObjectRandomizer(Random)
        val randomizedValues = (1..100).map { objectRandomizer.generate(TestNullableField::class) }
        assertTrue(randomizedValues.any { it.nullable == null })
        assertTrue(randomizedValues.any { it.nullable != null })
    }

    @Test
    fun generateEnum() {
        val objectRandomizer = ObjectRandomizer(Random)
        val randomValue = objectRandomizer.generate(TestEnum::class)
        assertTrue { randomValue == TestEnum.A || randomValue == TestEnum.B }
    }

    private class TestData1
    private class TestData2(val i: Int, val s: String)

    private abstract class TestBase(val s: String)
    private class TestInherited1(s: String) : TestBase(s)
    private class TestInherited2(val i: Int, s: String) : TestBase(s)

    private class TestNullableField(val nullable: String?)

    enum class TestEnum {
        A, B;
    }

    sealed class TestSealed(val s: String) {
        class A(s: String, val a: Int) : TestSealed(s)
        class B(s: String, val b: Int) : TestSealed(s)
        class C : TestSealed("c")
    }
}