package com.atsuya046.random

import kotlin.math.absoluteValue

private const val MAX_LENGTH = 30

object CharArrayRandomizer : Randomizer<CharArray> {
    private val sources = ('0'..'9') + ('a'..'z') + ('A'..'Z')

    override fun generate(): CharArray {
        val length = IntRandomizer.generate().absoluteValue % MAX_LENGTH + 1
        val chars = (0 until length).fold(mutableListOf()) { chars: MutableList<Char>, index: Int ->
            val char = sources[IntRandomizer.generate().absoluteValue % sources.size]
            chars.apply { add(index, char) }
        }
        return chars.toCharArray()
    }
}