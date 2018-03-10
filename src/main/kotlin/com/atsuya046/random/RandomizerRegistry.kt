package com.atsuya046.random

import kotlin.reflect.KClass

internal abstract class AbstractRandomizerRegistry {
    protected val randomizers: MutableMap<KClass<out Any>, Randomizer<Any>> = mutableMapOf()

    fun <T : Any> set(clazz: KClass<T>, randomizer: Randomizer<T>) {
        randomizers.set(clazz, randomizer)
    }

    abstract fun <T : Any> choose(clazz: KClass<T>): List<Randomizer<T>>
}

internal inline fun <reified T : Any> AbstractRandomizerRegistry.set(randomizer: Randomizer<T>) = this.set(T::class, randomizer)

internal class RandomizerRegistry : AbstractRandomizerRegistry() {
    private val registries: List<AbstractRandomizerRegistry> = listOf(InnerRandomizerRegistry)

    override fun <T : Any> choose(clazz: KClass<T>): List<Randomizer<T>> =
            registries.foldRight(emptyList()) { registry, randomizers ->
                registry.choose(clazz) + randomizers
            }
}

internal object InnerRandomizerRegistry : AbstractRandomizerRegistry() {
    init {
        set(IntRandomizer)
        set(LongRandomizer)
        set(FloatRandomizer)
        set(DoubleRandomizer)
        set(StringRandomizer)
        set(CharArrayRandomizer)
    }

    override fun <T : Any> choose(clazz: KClass<T>): List<Randomizer<T>> =
            randomizers[clazz]?.let { listOf(it as Randomizer<T>) } ?: emptyList()
}