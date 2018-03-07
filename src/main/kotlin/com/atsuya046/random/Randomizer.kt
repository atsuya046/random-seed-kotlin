package com.atsuya046.random

import kotlin.reflect.KClass

abstract class Randomizer<T : Any>(internal val type: KClass<T>) {
    abstract fun generate(): T
}
