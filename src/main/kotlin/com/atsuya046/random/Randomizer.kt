package com.atsuya046.random

import kotlin.reflect.KClass

abstract class Randomizer<T : Any>(internal val type: KClass<T>) {
    abstract fun generate(): T
}

class Random {

    private val registry = RandomizerRegistry()

    fun <T : Any> generate(): T = registry.choose<T>().generate()

    companion object {
        private val random = Random()

        fun <T : Any> generate(): T = random.generate()
    }
}