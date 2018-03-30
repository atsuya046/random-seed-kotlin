package com.atsuya046.random

import kotlin.reflect.KClass

internal abstract class AbstractRandomizerRegistry : Choosable, Registrable {
    protected val randomizers: MutableMap<KClass<out Any>, Randomizer<Any>> = mutableMapOf()

    override fun <T : Any> register(clazz: KClass<T>, randomizer: Randomizer<T>) {
        randomizers.set(clazz, randomizer)
    }

    override fun <T : Any> choose(clazz: KClass<T>): List<Randomizer<T>> =
            randomizers[clazz]?.let { listOf(it as Randomizer<T>) } ?: emptyList()
}

internal interface Choosable {
    fun <T : Any> choose(clazz: KClass<T>): List<Randomizer<T>>
}

internal interface Registrable {
    fun <T : Any> register(clazz: KClass<T>, randomizer: Randomizer<T>)
}

internal inline fun <reified T : Any> Registrable.register(randomizer: Randomizer<T>) = this.register(T::class, randomizer)

internal class RandomizerRegistry : AbstractRandomizerRegistry() {
    private val customRegistry = CustomRandomizerRegistry()

    private val registries: List<AbstractRandomizerRegistry> = listOf(customRegistry, InnerRandomizerRegistry)

    override fun <T : Any> register(clazz: KClass<T>, randomizer: Randomizer<T>) = customRegistry.register(clazz, randomizer)

    override fun <T : Any> choose(clazz: KClass<T>): List<Randomizer<T>> =
            registries.foldRight(emptyList()) { registry, randomizers ->
                registry.choose(clazz) + randomizers
            }
}

internal object InnerRandomizerRegistry : AbstractRandomizerRegistry() {
    init {
        register(IntRandomizer)
        register(LongRandomizer)
        register(FloatRandomizer)
        register(DoubleRandomizer)
        register(StringRandomizer)
        register(CharArrayRandomizer)
    }
}

internal class CustomRandomizerRegistry : AbstractRandomizerRegistry()