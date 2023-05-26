import io.qameta.allure.Step
import io.qameta.allure.Allure.step

typealias Decorator<T> = (() -> T) -> T

/**
 * Applies the given decorators from left to right.
 * Usage:
 * </pre>{@code
 * fun italic(f: () -> String) = "<i>${f()}</i>"
 * fun hello() = decorateWith(::italic) {
 *     "hello"
 * }
 * }<pre>
 */
@Step("expected: {0}")
fun <T> decorateWith(vararg decorators: Decorator<T>, function: () -> T): T {
    // return decorators.fold(initial = function()) { result, decorator -> decorator { result } }
    val resultCollection = decorators.fold(initial = function()) { result, decorator -> decorator { result } }
    println("====")
    println(resultCollection)
    println("----")
   // resultCollection.forEach { println(it) }
    return resultCollection
}

