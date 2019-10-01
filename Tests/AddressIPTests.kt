import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AddressIPTests {
    @Test
    fun toStringTest() {
        var t = AddressIP(mutableListOf(0,10,99,255))
        Assert.assertEquals("0.10.99.255",t.toString())
        t = AddressIP(mutableListOf(99,234,9,0))
        Assert.assertEquals("99.234.9.0",t.toString())
    }

    @Test
    fun compareToTest() {
        val t = AddressIP(mutableListOf(0,10,99,255))
        Assert.assertEquals(1,t.compareTo(AddressIP(mutableListOf(0,10,99,254))))
        Assert.assertEquals(-1,t.compareTo(AddressIP(mutableListOf(1,10,99,0))))
        Assert.assertEquals(0,t.compareTo(AddressIP(mutableListOf(0,10,99,255))))
    }

    @Test
    fun getNextIPorNullTest() {
        val t = AddressIP(mutableListOf(1,2,MAX_VALUE_IN_IP,4))
        //Наверное, не очень хорошо в тестировании одного метода опираться на другой
        //Но isEquals очень простой и протестирован
        assertTrue(t.getNextIPorNull()!!.isEqual((AddressIP(mutableListOf(1,2,MAX_VALUE_IN_IP,5)))))
        //По-хорошему надо сделать тест для переполнения каждого сегмента.
        val t2 = AddressIP(mutableListOf(MAX_VALUE_IN_IP,MAX_VALUE_IN_IP,MAX_VALUE_IN_IP,MAX_VALUE_IN_IP)).getNextIPorNull()
        assertNull(t2)
    }

    @Test
    fun isEqualTest() {
        val t = AddressIP(mutableListOf(1,2,255,4))
        val tPositive = AddressIP(mutableListOf(1,2,255,4))
        val tNegative1 = AddressIP(mutableListOf(1,2,0,4))
        val tNegative2 = AddressIP(mutableListOf(3,2,255,4))

        assertTrue(t.isEqual(tPositive))
        assertFalse(t.isEqual(tNegative1))
        assertFalse(t.isEqual(tNegative2))
    }
}