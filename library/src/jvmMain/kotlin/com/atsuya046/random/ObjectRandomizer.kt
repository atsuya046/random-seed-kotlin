package com.atsuya046.random

import kotlin.math.abs
import kotlin.reflect.KClass

actual class ObjectRandomizer actual constructor(private val random: Random) {

    actual fun <T : Any> generate(clazz: KClass<T>): T = when {
        clazz.isAbstract -> generateSubclassInstance(clazz)
        clazz.isSealed -> generateSealedSubclassInstance(clazz)
        else -> generateInstance(clazz)
    }

    private fun <T : Any> generateSealedSubclassInstance(clazz: KClass<T>): T {
        assert(clazz.isSealed)
        val subclass = clazz.sealedSubclasses.shuffled().first()
        return generateInstance(subclass)
    }

    private fun <T : Any> generateSubclassInstance(clazz: KClass<T>): T {
        assert(clazz.isAbstract)
        return TODO()
//
//        val scanner = FastClasspathScanner().matchSubclassesOf(clazz.java) {
//            // nothing to do
//        }
//        val result = scanner.scan()
//        val subClazzes = result.getNamesOfSubclassesOf(clazz.java).map { Class.forName(it).kotlin as KClass<T> }
//
//        return generateInstance(subClazzes.shuffled().first())
    }

    private fun <T : Any> generateInstance(clazz: KClass<T>): T {
        if (clazz::java.get().isEnum) {
            val enumConstants = clazz::java.get().enumConstants
            assert(enumConstants.isNotEmpty())

            val randomIndex = abs(random.generate<Int>()) % enumConstants.size
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
            constructor.call()
        } else {
            constructor.call(*arguments)
        } ?: throw RuntimeException("Cannot get constructor.")
    }
}