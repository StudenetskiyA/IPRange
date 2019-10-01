//Импорт только для логов в файл.
//Для Андроид воспользовался бы удобным LogCat.
import java.io.PrintWriter
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val MAX_VALUE_IN_IP = 3
const val USE_RECURSIVE = true


fun main() {
    log("App started.")
    val myInputMethod = MyInputMethod()


//val ip1 = myInputMethod.getIPAddress(StringAsker(System.`in`,System.out))
//val ip2 = myInputMethod.getIPAddress(StringAsker(System.`in`,System.out))
    val ip1 = AddressIP(mutableListOf(1, 2, 1, 0))
    val ip2 = AddressIP(mutableListOf(1, 2, 1, 0))
    printRangeBetweenIP(ip1, ip2)
}


//Выводим список адресов между двумя IP
fun printRangeBetweenIP(_ip1: AddressIP, _ip2: AddressIP) {
    var ip1 = _ip1
    var ip2 = _ip2
    //Меняем местами, если первый больше второго
    if (ip1 > ip2) {
        val tmp = ip2
        ip2 = ip1
        ip1 = tmp
    }

    if (USE_RECURSIVE)
        recursiveMethod(ip1, ip2)
    else
        cycleMethod(ip1, ip2)
}

fun recursiveMethod(ip1: AddressIP, ip2: AddressIP) {
    System.out.println("${ip1.address[0]}.${ip1.address[1]}.${ip1.address[2]}.${ip1.address[3]}")
    val nextIP = ip1.getNextIP()

    if (nextIP.isEqual(ip2)) return
    recursiveMethod(nextIP, ip2)
}

fun cycleMethod(ip1: AddressIP, ip2: AddressIP) {
    for (i0 in ip1.address[0]..ip2.address[0]) {
        val f1 = if (ip1.address[0] != ip2.address[0]) MAX_VALUE_IN_IP else ip2.address[1]
        for (i1 in ip1.address[1]..f1) {
            val f2 = if (ip1.address[1] != ip2.address[1]) MAX_VALUE_IN_IP else ip2.address[2]
            for (i2 in ip1.address[2]..f2) {
                val f3 = if (ip1.address[2] != ip2.address[2]) MAX_VALUE_IN_IP else ip2.address[3]
                for (i3 in ip1.address[3]..f3) {
                    System.out.println(AddressIP(mutableListOf(i0, i1, i2, i3)).toString())
                }
                ip1.address[2]++
            }
            ip1.address[1]++
        }
        ip1.address[0]++
    }
}

fun log(text: String) {
    val printWriter = PrintWriter(FileWriter("application.log", true))
    printWriter.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + ": " + text)
    printWriter.close()
}

