import java.util.*
import kotlin.test.assertTrue

class MyInputMethod {
    private fun  getString() : String {
        val scan = Scanner(System.`in`)
        val txt = scan.nextLine()
        log("User enter $txt")
        return txt
    }

    fun getIPAddress() : AddressIP {
        var ip : AddressIP?
        while (true) {
            println("Enter correct ip-address")
            ip = this.getString().getIPAddressOrNull()
            if (ip!=null) return ip
            println("Incorrect IP, need form XXXX.XXXX.XXXX.XXXX . Try again.")
        }
    }
}

fun String.isCorrectIP() : Boolean {
    val p1: Int
    val p2: Int
    val p3: Int

    val n1: Int?
    val n2: Int?
    val n3: Int?
    val n4: Int?

    p1 = this.indexOf(".",0)
    if (p1==-1) return false
    n1 = this.substring(0,p1).toIntOrNull()
    if (n1==null) return false
    p2 = this.indexOf(".",p1+1)
    if (p2==-1) return false
    n2 = this.substring(p1+1,p2).toIntOrNull()
    if (n2==null) return false
    p3 = this.indexOf(".",p2+1)
    if (p3==-1) return false
    n3 = this.substring(p2+1,p3).toIntOrNull()
    if (n3==null) return false
    n4 = this.substring(p3+1,this.length).toIntOrNull()
    if (n4==null) return false

    if (n1>255 || n2>255 || n3>255 || n4>255 || n1<0 || n2<0 || n3<0|| n4<0) return false

    return true
}

//Можно было сделать без null, но тогда в теории мы могли бросать исключение
//По аналогии с String.toInt()
fun String.getIPAddressOrNull() : AddressIP? {
    return if (!this.isCorrectIP())
        null
    else {
        AddressIP(mutableListOf())
    }
}