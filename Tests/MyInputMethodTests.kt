import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.*
import java.io.PrintStream
import java.io.InputStream
import java.util.*


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


    //Вдруг ввод как-то изменится, и мы его тоже протестируем.
    //Пока не очень понятно, как тестировать ввод с клавиатуры.
    fun mockUserInput(userInput:String):String {
        return userInput
    }




    @Test
    fun getStringTest() {
        val asker = mock(StringAsker::class.java)

        Mockito.`when`(asker.ask("Give a number between 1 and 10")).thenReturn("10")
       // assertEquals(getBoundStringFromUser(asker),"10")
    }
}