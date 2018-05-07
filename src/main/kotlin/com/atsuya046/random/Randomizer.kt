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

    fun <A : Any> generateNullable(clazz: KClass<A>): A? = when (IntRandomizer.generate() % 2) {
        0 -> null
        else -> generate(clazz)
    }

    inline fun <reified T : Any> generateNullable(): T? = generateNullable(T::class)

    override fun <T : Any> register(clazz: KClass<T>, randomizer: Randomizer<T>) = registry.register(clazz, randomizer)

    inline fun <reified T : Any> register(randomizer: Randomizer<T>) = register(T::class, randomizer)

    inline fun <reified T : Any> register(crossinline generator: () -> T) = register(T::class, object : Randomizer<T>() {
        override fun generate(): T = generator()
    })

    companion object : Random() {
        fun newInstance(): Random = object : Random() {}
    }
}