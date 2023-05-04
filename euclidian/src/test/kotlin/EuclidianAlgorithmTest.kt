import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TestFunctions {
    @Test
    fun testLcm() {
        assertEquals(1024, lcm(2, 1024))
        assertEquals(273, lcm(13, 21))
    }

    @Test
    fun testGcd() {
        assertEquals(-30, gcd(-30, 180))
        assertEquals(1, gcd(13, 21))
    }
}
