package com.atsuya046.random

import java.util.Random
import kotlin.reflect.KClass

abstract class Randomizer<T: Any>(internal val type: KClass<T>) {
    abstract fun generate(): T
}

object IntRandomizer : Randomizer<Int>(Int::class) {
    private val random = Random()
    override fun generate(): Int = random.nextInt()
}

object LongRandomizer : Randomizer<Long>(Long::class) {
    private val random = Random()
    override fun generate(): Long = random.nextLong()
}

object FloatRandomizer : Randomizer<Float>(Float::class) {
    private val random = Random()
    override fun generate(): Float = random.nextFloat()
}

object DoubleRandomizer : Randomizer<Double>(Double::class) {
    private val random = Random()
    override fun generate(): Double = random.nextDouble()
}
