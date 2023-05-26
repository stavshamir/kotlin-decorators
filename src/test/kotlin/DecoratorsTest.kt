import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import io.qameta.allure.Step

internal class DecoratorsTest {

    @Test
    fun `decorateWith should work correctly with a single String decorator`() {
        fun italic(f: () -> String) = "<i>${f()}</i>"

        fun decorated() = decorateWith(::italic) {
            "decorated"
        }

        assertEquals("<i>decorated</i>", decorated())
    }

    @Test
    fun `decorateWith should work correctly with multiple String decorators`() {
        fun italic(f: () -> String) = "<i>${f()}</i>"
        fun bold(f: () -> String) = "<b>${f()}</b>"

        fun decorated() = decorateWith(::italic, ::bold) {
            "decorated"
        }

        assertEquals("<b><i>decorated</i></b>", decorated())
    }

    @Test
    fun `decorateWith should work correctly with a single Int decorator`() {
        fun returnEvenResultOrZero(f: () -> Int): Int {
            val returnValue = f()
            return if (returnValue % 2 == 0) returnValue else 0
        }


        fun decorated(x: Int, y: Int) = decorateWith(::returnEvenResultOrZero) {
            x + y
        }

        assertEquals(2, decorated(1, 1))
        assertEquals(0, decorated(0, 1))
    }

    @Test
    fun `decorateWith should work correctly with an object`() {
        @Step("inner: {0}")
        fun returnObject(f: () -> SomeData): SomeData {
            return f()
        }

        @Step("top: {0}")
        fun decorated(id: Int, name: String) = decorateWith(::returnObject) {
            SomeData(id,name)
        }

        assertEquals(SomeData(5,"aaa"), decorated(5, "aaa"))
    }

    @Test
    fun `decorateWith should work correctly with an object and extension method`() {
        @Step("inner: {0}")
        fun returnObject(f: () -> SomeData): SomeData {
            return f()
        }

        @Step("top: {0}")
        fun SomeData.decorated(test: String) = decorateWith(::returnObject) {
            this
        }

        assertEquals(SomeData(5,"aaa"), SomeData(5,"aaa").decorated( "bbb"))
    }
}