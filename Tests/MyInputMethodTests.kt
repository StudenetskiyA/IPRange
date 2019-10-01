import org.junit.Test
import kotlin.test.*
import java.io.PrintStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class MyInputMethodTests {
    @Test
    fun isCorrectIPTest() {
        val testNegativeList = listOf("no dhdffd","0990.988.009.","-2.0.9.199","","0.2.3.256")
        for (t in testNegativeList)
            assertFalse(t.isCorrectIP())
         val testPositiveList = listOf("1.0.0.1","255.255.255.255","0.0.0.0","123.244.244.234")
        for (t in testPositiveList)
            assertTrue(t.isCorrectIP())
    }

    @Test
    fun getIPAddressOrNullTest() {
        val t = AddressIP(mutableListOf(0,2,255,99))
        val t1 = "0.2.255.99".getIPAddressOrNull()
        assertNotNull(t1)
        assertEquals(t.address[0],t1.address[0])
        assertEquals(t.address[1],t1.address[1])
        assertEquals(t.address[2],t1.address[2])
        assertEquals(t.address[3],t1.address[3])

        val negativeIPTestList = listOf("-0.2d.255.99".getIPAddressOrNull(),"".getIPAddressOrNull(),
            "bbv".getIPAddressOrNull(),"266.0.2.1".getIPAddressOrNull())
        for (n in negativeIPTestList)
            assertNull(n)
    }

    @Test
    fun getStringInputTest() {
        val inputOutput = MyInputMethod()
        val userInput = "20.10.20.255"
        val input = ByteArrayInputStream(userInput.toByteArray())
        System.setIn(input)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val result = inputOutput.getString()
        assertEquals("20.10.20.255", result)
    }

    @Test
    fun getIPAddressPositiveTest() {
        val inputOutput = MyInputMethod()
        val userInput = "20.10.20.250"
        val input = ByteArrayInputStream(userInput.toByteArray())
        System.setIn(input)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val result = inputOutput.getIPAddress()
        assertEquals("Enter correct ip-address : ", outContent.toString())
        assertTrue(result.isEqual((AddressIP(mutableListOf(20,10,20,250)))))
    }
}