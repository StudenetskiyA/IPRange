import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class PrintRangeBetweenIPTests {
    val ip1 = AddressIP(mutableListOf(100,200,30,2))
    val ip2 = AddressIP(mutableListOf(100,200,30,4))
    val ip3 = AddressIP(mutableListOf(100,200,30,5))

    val ip4= AddressIP(mutableListOf(100,255,255,255))
    val ip5= AddressIP(mutableListOf(101,0,0,1))

    @Test
    fun recursiveMethodOneResultOverMax() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        printRangeBetweenIP(ip4,ip5)
        assertEquals("101.0.0.0\r\n", outContent.toString())
    }
    @Test
    fun recursiveMethodTwoNear() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        printRangeBetweenIP(ip2,ip3)
        assertEquals("", outContent.toString())
    }
    @Test
    fun recursiveMethodOneResult() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        printRangeBetweenIP(ip1,ip2)
        assertEquals("100.200.30.3\r\n", outContent.toString())
    }
    @Test
    fun recursiveMethodTwoResult() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        printRangeBetweenIP(ip1,ip3)
        assertEquals("100.200.30.3\r\n100.200.30.4\r\n", outContent.toString())
    }
    @Test
    fun recursiveMethodTwoEqual() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        printRangeBetweenIP(ip1,ip1)
        assertEquals("", outContent.toString())
    }
}