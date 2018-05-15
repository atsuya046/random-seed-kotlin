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

```kotlin
// generate Int
val i = Random.generate<Int>()

// generate String
val str = Random.generate<String>()

// generate your model
class YourModel(val foo: String, val bar: Int)
val model = Random.generate<YourModel>()


// register your custom Randomizer
val always5 = object : Randomizer<Int>() {
    override fun generate(): Int = 5
}
Random.register(always5) 
val five = Random.generate<Int>()
// and, you can also register in this way
Random.register { 5 } 
```