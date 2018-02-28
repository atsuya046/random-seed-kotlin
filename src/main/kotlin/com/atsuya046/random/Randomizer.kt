package com.atsuya046.random

import java.util.*

interface Randomizer<out T> {
    fun generate(): T
}

object IntRandomizer : Randomizer<Int> {
    private val random = Random()
    override fun generate(): Int = random.nextInt()
}

object LongRandomizer : Randomizer<Long> {
    private val random = Random()
    override fun generate(): Long = random.nextLong()
}

object FloatRandomizer : Randomizer<Float> {
    private val random = Random()
    override fun generate(): Float = random.nextFloat()
}

object DoubleRandomizer : Randomizer<Double> {
    private val random = Random()
    override fun generate(): Double = random.nextDouble()
}
