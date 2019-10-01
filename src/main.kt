//Импорт только для логов в файл.
//Для Андроид воспользовался бы удобным LogCat.
import java.io.PrintWriter
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val MAX_VALUE_IN_IP = 255

fun main() {
    log("App started.")
    val myInputMethod = MyInputMethod()

    val ip1 = myInputMethod.getIPAddress()
    val ip2 = myInputMethod.getIPAddress()

    if (ip1.getNextIPorNull() != null)
        printRangeBetweenIP(ip1, ip2)
    //Если нам подали на ввод меньший IP и он максимальный, значит и второй такой же - делать ничего не надо.
}

//Выводим список адресов между двумя IP
fun printRangeBetweenIP(ip1: AddressIP, ip2: AddressIP) {
    val pair = changeOrderIPIfItNeeds(ip1,ip2)
    recursiveMethod(pair.first, pair.second)
}

fun changeOrderIPIfItNeeds(_ip1:AddressIP,_ip2:AddressIP) :Pair<AddressIP,AddressIP> {
    //Меняем местами, если первый больше второго
    var ip1 = _ip1
    var ip2 = _ip2
    if (ip1 > ip2) {
        val tmp = ip2
        ip2 = ip1
        ip1 = tmp
    }
    return Pair(ip1,ip2)
}

fun recursiveMethod(ip1: AddressIP, ip2: AddressIP) {
    val nextIP = ip1.getNextIPorNull()
    //Здесь мы точно знаем, что он не null - адреса отсортированы, корректны и т.д.
    if (nextIP!!.isEqual(ip2) || nextIP!!.compareTo(ip2)>0) return
    System.out.println("${nextIP.address[0]}.${nextIP.address[1]}.${nextIP.address[2]}.${nextIP.address[3]}")
    recursiveMethod(nextIP, ip2)
}

fun log(text: String) {
    val printWriter = PrintWriter(FileWriter("application.log", true))
    printWriter.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + ": " + text)
    printWriter.close()
}

