package com.atsuya046.random

object StringRandomizer : Randomizer<String>(String::class) {
    override fun generate(): String = CharArrayRandomizer.generate().toString()
}
