package com.atsuya046.random

internal abstract class AbstractRandomizerRegistry {
    protected val randomizers: MutableMap<Class<out Any>, Randomizer<Any>> = mutableMapOf()

    fun <T : Any> set(clazz: Class<T>, randomizer: Randomizer<T>) {
        randomizers.set(clazz, randomizer)
    }

    abstract fun <T : Any> choose(clazz: Class<T>): List<Randomizer<T>>
}

internal inline fun <reified T : Any> AbstractRandomizerRegistry.set(randomizer: Randomizer<T>) = this.set(T::class.java, randomizer)

internal class RandomizerRegistry : AbstractRandomizerRegistry() {
    private val registries: List<AbstractRandomizerRegistry> = listOf(InnerRandomizerRegistray)

    override fun <T : Any> choose(clazz: Class<T>): List<Randomizer<T>> =
            registries.foldRight(emptyList()) { registry, randomizers ->
                registry.choose(clazz) + randomizers
            }
}

internal object InnerRandomizerRegistray : AbstractRandomizerRegistry() {
    init {
        set(IntRandomizer)
        set(LongRandomizer)
        set(FloatRandomizer)
        set(DoubleRandomizer)
        set(StringRandomizer)
        set(CharArrayRandomizer)
    }

    override fun <T : Any> choose(clazz: Class<T>): List<Randomizer<T>> =
            randomizers[clazz]?.let { listOf(it as Randomizer<T>) } ?: emptyList()
}