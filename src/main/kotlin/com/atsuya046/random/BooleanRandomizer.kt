package com.atsuya046.random

import java.util.Random

object BooleanRandomizer : Randomizer<Boolean>() {
    private val random = Random()
    override fun generate(): Boolean {
        return random.nextBoolean()
    }
}