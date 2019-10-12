package com.atsuya046.random

import kotlin.random.Random

object BooleanRandomizer : Randomizer<Boolean>() {
    override fun generate(): Boolean = Random.nextBoolean()
}