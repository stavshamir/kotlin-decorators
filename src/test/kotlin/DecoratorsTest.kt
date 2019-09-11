import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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


}