//Импорт только для логов в файл.
//Для Андроид воспользовался бы удобным LogCat.
import java.io.PrintWriter
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val MAX_VALUE_IN_IP = 3

fun main() {
    log("App started.")
    val myInputMethod = MyInputMethod()

    val ip1 = myInputMethod.getIPAddress(StringAsker(System.`in`, System.out))
    val ip2 = myInputMethod.getIPAddress(StringAsker(System.`in`, System.out))

    if (ip1.getNextIPorNull() != null)
        printRangeBetweenIP(ip1.getNextIPorNull()!!, ip2)
    //Если нам подали на ввод меньший IP и он максимальный, значит и второй такой же - делать ничего не надо.
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
    recursiveMethod(ip1, ip2)
}

fun recursiveMethod(ip1: AddressIP, ip2: AddressIP) {
    System.out.println("${ip1.address[0]}.${ip1.address[1]}.${ip1.address[2]}.${ip1.address[3]}")
    val nextIP = ip1.getNextIPorNull()
    //Здесь мы точно знаем, что он не null
    if (nextIP!!.isEqual(ip2)) return
    recursiveMethod(nextIP, ip2)
}

fun log(text: String) {
    val printWriter = PrintWriter(FileWriter("application.log", true))
    printWriter.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + ": " + text)
    printWriter.close()
}

