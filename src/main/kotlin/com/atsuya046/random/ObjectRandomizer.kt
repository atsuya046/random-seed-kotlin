package com.atsuya046.random

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner
import kotlin.reflect.KClass
import kotlin.reflect.jvm.javaConstructor

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
        if (clazz::java.get().isEnum) {
            val enumConstants = clazz::java.get().enumConstants
            assert(enumConstants.isNotEmpty())

            val randomIndex = random.generate<Int>() % enumConstants.size
            return enumConstants[randomIndex]
        }

        val constructor = clazz.constructors.firstOrNull() ?: throw RuntimeException("Public constructor is not found.")
        val arguments = constructor.parameters.fold(emptyArray<Any?>()) { acc, parameter ->
            val argument = with(parameter.type.classifier as KClass<*>?) {
                if (parameter.type.isMarkedNullable) {
                    this?.let { random.generateNullable(it) }
                } else {
                    this?.let { random.generate(it) }
                }
            }
            acc + argument
        }

        return if (arguments.isEmpty()) {
            constructor.javaConstructor?.newInstance()
        } else {
            constructor.javaConstructor?.newInstance(*arguments)
        } ?: throw RuntimeException("Cannot get constructor.")
    }
}