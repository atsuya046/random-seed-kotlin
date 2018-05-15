# random-seed-kotlin

[![](https://jitpack.io/v/atsuya046/random-seed-kotlin.svg)](https://jitpack.io/#atsuya046/random-seed-kotlin)

This provides random kotlin object.

You can get easily random numbers, string and your domain models. 

# Add dependencies
root build.gradle
```groovy
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```

add the dependency
```groovy
dependencies {
    implementation 'com.github.atsuya046:random-seed-kotlin:0.0.3'
}
```

# How to use

generate random values.

```kotlin
// generate random Int
Random.generate<Int>()

// generate random String
Random.generate<String>()

// generate random your model
class YourModel(val foo: String, val bar: Int)
Random.generate<YourModel>()
```

register your custom Randomizer
```kotlin
val always5 = object : Randomizer<Int>() {
    override fun generate(): Int = 5
}
Random.register(always5)
// you can also register in this way
// Random.register { 5 }

// generate 5 
Random.generate<Int>()
```