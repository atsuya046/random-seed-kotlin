# random-seed-kotlin

This provides random kotlin object.

You can get easily random numbers, string and your domain models. 

# How to use

```kotlin
// generate Int
val i = Random.generate<Int>()

// generate String
val str = Random.generate<String>()

// generate your model
class YourModel(val foo: String, val bar: Int)
val model = Random.generate<YourModel>()

```