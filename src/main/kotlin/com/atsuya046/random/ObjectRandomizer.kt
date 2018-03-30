package com.atsuya046.random

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner
import java.lang.reflect.Constructor
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

class ObjectRandomizer(private val random: Random) {

    fun <T : Any> generate(clazz: KClass<T>): T = if (clazz.isAbstract) generateSubclassInstance(clazz)
    else generateInstance(clazz)

    private fun <T : Any> generateSubclassInstance(clazz: KClass<T>): T {
        assert(clazz.isAbstract)

        val scanner = FastClasspathScanner().matchSubclassesOf(clazz.java) {
            // nothing to do
        }
        val result = scanner.scan()
        val subClazzes = result.getNamesOfSubclassesOf(clazz.java).map { Class.forName(it).kotlin as KClass<T> }

        return generateInstance(subClazzes.shuffled().first())
    }

    private fun <T : Any> generateInstance(clazz: KClass<T>): T {
        val constructor = clazz.java.constructors.firstOrNull { Modifier.isPublic(it.modifiers) } as Constructor<T>?
                ?: throw RuntimeException("Public constructor is not found.")
        val arguments = constructor.parameterTypes.map {
            random.generate(it.kotlin)
        }.toTypedArray()

        return if (arguments.isEmpty()) constructor.newInstance() else constructor.newInstance(*arguments)
    }
}