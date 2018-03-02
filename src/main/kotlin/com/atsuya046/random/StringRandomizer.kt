package com.atsuya046.random

object StringRandomizer : Randomizer<String> {
    override fun generate(): String = CharArrayRandomizer.generate().toString()
}
