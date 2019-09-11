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
fun <T> decorateWith(vararg decorators: Decorator<T>, function: () -> T): T {
    return decorators.fold(initial = function()) { result, decorator -> decorator { result } }
}

