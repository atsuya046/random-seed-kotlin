package com.atsuya046.random

import kotlin.random.Random

object IntRandomizer : Randomizer<Int>() {
    override fun generate(): Int = Random.nextInt()
}

object LongRandomizer : Randomizer<Long>() {
    override fun generate(): Long = Random.nextLong()
}

object FloatRandomizer : Randomizer<Float>() {
    override fun generate(): Float = Random.nextFloat()
}

object DoubleRandomizer : Randomizer<Double>() {
    override fun generate(): Double = Random.nextDouble()
}
