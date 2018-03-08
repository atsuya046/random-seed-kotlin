package com.atsuya046.random

import java.lang.reflect.Constructor
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

class ObjectRandomizer(private val random: Random) {

    fun <T : Any> generate(clazz: KClass<T>): T {
        val constructor = clazz.java.constructors.firstOrNull { Modifier.isPublic(it.modifiers) } as Constructor<T>?
                ?: throw RuntimeException("Public constructor is not found.")
        val arguments = constructor.parameterTypes.map {
            random.generate(it.kotlin)
        }.toTypedArray()

        return if (arguments.isEmpty()) constructor.newInstance() else constructor.newInstance(*arguments)
    }
}