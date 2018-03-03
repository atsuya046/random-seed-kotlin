package com.atsuya046.random

class RandomizerRegistry {

    var randomizers: MutableSet<Randomizer<*>> = mutableSetOf(
            IntRandomizer,
            LongRandomizer,
            FloatRandomizer,
            DoubleRandomizer,
            StringRandomizer,
            CharArrayRandomizer
    )

    inline fun <reified T : Any> choose(): Randomizer<T> = randomizers
            .firstOrNull { it.type == T::class } as Randomizer<T>?
            ?: throw IllegalArgumentException("Cannot find Randomizer.")
}