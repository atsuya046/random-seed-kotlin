package com.atsuya046.random

abstract class Randomizer<out T : Any> {
    abstract fun generate(): T
}

class Random {
    private val registry = RandomizerRegistry()

    fun <T : Any> generate(clazz: Class<T>): T = registry.choose(clazz).first().generate()

    inline fun <reified T : Any> generate(): T = generate(T::class.java)
}