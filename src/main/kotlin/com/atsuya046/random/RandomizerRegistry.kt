package com.atsuya046.random

internal class RandomizerRegistry {

    var randomizers: MutableSet<Randomizer<*>> = mutableSetOf(
            IntRandomizer,
            LongRandomizer,
            FloatRandomizer,
            DoubleRandomizer,
            StringRandomizer,
            CharArrayRandomizer
    )

    inline fun <reified T : Any> choose(): Randomizer<T> = randomizers.find { it.type == T::class } as? Randomizer<T>
            ?: throw IllegalArgumentException("Can not find Randomizer for ${T::class}")
}