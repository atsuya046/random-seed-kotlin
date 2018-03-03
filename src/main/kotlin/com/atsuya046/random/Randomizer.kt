package com.atsuya046.random

import kotlin.reflect.KClass

abstract class Randomizer<T : Any>(val type: KClass<T>) {
    abstract fun generate(): T
}

class Random {
    val registry = RandomizerRegistry()

    inline fun <reified T : Any> generate(): T = registry.choose<T>().generate()
}