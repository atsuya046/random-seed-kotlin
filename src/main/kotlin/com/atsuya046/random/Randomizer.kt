package com.atsuya046.random

import kotlin.reflect.KClass

/**
 * Abstract random generator for specific class.
 */
abstract class Randomizer<out T : Any> {
    /**
     * Generate random object.
     */
    abstract fun generate(): T
}

/**
 * Random object generator.
 */
abstract class Random : Registrable {
    private val registry = RandomizerRegistry()

    /**
     * Generate random object.
     */
    fun <T : Any> generate(clazz: KClass<T>): T =
            registry.choose(clazz).firstOrNull()?.generate()
                    ?: ObjectRandomizer(this).generate(clazz)

    /**
     * Generate random object.
     */
    inline fun <reified T : Any> generate(): T = generate(T::class)

    /**
     * Generate random object or null.
     */
    fun <A : Any> generateNullable(clazz: KClass<A>): A? = when (IntRandomizer.generate() % 2) {
        0 -> null
        else -> generate(clazz)
    }

    /**
     * Generate random object or null.
     */
    inline fun <reified T : Any> generateNullable(): T? = generateNullable(T::class)

    /**
     * Register specific randomizer.
     */
    override fun <T : Any> register(clazz: KClass<T>, randomizer: Randomizer<T>) = registry.register(clazz, randomizer)

    /**
     * Register specific randomizer.
     */
    inline fun <reified T : Any> register(randomizer: Randomizer<T>) = register(T::class, randomizer)

    /**
     * Register specific randomizer.
     */
    inline fun <reified T : Any> register(crossinline generator: () -> T) = register(T::class, object : Randomizer<T>() {
        override fun generate(): T = generator()
    })

    companion object : Random() {
        /**
         * Create new random generator.
         */
        fun newInstance(): Random = object : Random() {}
    }
}