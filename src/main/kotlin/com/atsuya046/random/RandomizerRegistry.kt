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

    fun <T : Any> choose(): Randomizer<T> = randomizers.find { it as? Randomizer<T> != null } as Randomizer<T>
}