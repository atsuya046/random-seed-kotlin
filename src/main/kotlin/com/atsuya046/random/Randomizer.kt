package com.atsuya046.random

import kotlin.reflect.KClass

abstract class Randomizer<out T : Any> {
    abstract fun generate(): T
}

abstract class Random : Registrable {
    private val registry = RandomizerRegistry()

    fun <T : Any> generate(clazz: KClass<T>): T =
            registry.choose(clazz).firstOrNull()?.generate()
                    ?: ObjectRandomizer(this).generate(clazz)

    inline fun <reified T : Any> generate(): T = generate(T::class)

    override fun <T : Any> register(clazz: KClass<T>, randomizer: Randomizer<T>) = registry.register(clazz, randomizer)

    companion object : Random() {
        fun newInstance(): Random = object : Random() {}
    }
}