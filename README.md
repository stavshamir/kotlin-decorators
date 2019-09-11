# Python-like function decorators for Kotlin

*TL;DR: This repository contains the minimalistic function `decorateWith` that provides the means to apply single or multiple python-like function decorators to any function in a canonical way*

### Decorators in python
Python [decorators][decorators-link] are a really nice feature. They decleratively extend the functionality of a function without explicitly modifying its body, allowing for higher readablity and code reuse.
A classic example for the use of a decorator is wrapping the output of a function with html tags:
```python
@italic
def hello():
    return "hello"
 
hello()
```

### Decorators and annotations in Kotlin
Kotlin does not have this feature. Annotations may somewhat resemble decorators, but while decorators actually run code, annotations simply provide metadata and do nothing on their own - we must separately write and run a function to process the annotations. Annotations are very powerful, but require more work to get going than python decorators.

#### Decorating a function with zero parameters
I decided to play around with Kotlin to try and implement a python-like decorators. The requirements are that the result should be declerative, it should not interrupt the flow of the decorated function, and should be declared at function decleration and not at function use. Using higher-order functions seems appropriate and looks like this:
```kotlin
fun italic(function: () -> String) = "<i>${function()}</i>"

fun hello() = italic {
    "hello"
}

assert("<i>hello</i>" == hello())
```

The function `italic` receives a function that returns a `String`, and returns a `String`. Then `hello` uses the lambda as last parameter syntax, giving IMO the looks and feel of    a python-like decorator.

### Generic decorator
This repository contains the minimalistic function `decorateWith` that provides the means to apply single or multiple decorators to any function in a canonical way:
```kotlin
fun <T> printResult(function: () -> T): T {
     val result = function()
    println("The result is: $result")
    return result
}

fun add(x: Int, y: Int) = decorateWith(::printResult) {
    x + y
}

fun hello(name: String) = decorateWith(::italic, ::printResult) {
    "hello $name"
}
```

[decorators-link]: https://www.thecodeship.com/patterns/guide-to-python-function-decorators/