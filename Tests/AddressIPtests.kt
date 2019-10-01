import org.junit.Assert
import org.junit.Test

class AddressIPtests {
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
}