import org.junit.Test
import kotlin.test.assertTrue

class ChangeOrderIPIfItNeedsTests {
    private val ip1 = AddressIP(mutableListOf(100, 200, 30, 2))
    private val ip2 = AddressIP(mutableListOf(100, 200, 30, 4))

    @Test
    fun changeOrder_NeedChange() {
        val pair = changeOrderIPIfItNeeds(ip2, ip1)
        assertTrue(pair.first.isEqual(ip1))
    }

    @Test
    fun changeOrder_NoNeedChange() {
        val pair = changeOrderIPIfItNeeds(ip1, ip2)
        assertTrue(pair.first.isEqual(ip1))
    }

    @Test
    fun changeOrder_Equal() {
        val pair = changeOrderIPIfItNeeds(ip1, ip1)
        assertTrue(pair.first.isEqual(ip1))
    }
}