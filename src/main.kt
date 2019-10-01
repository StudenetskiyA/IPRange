//Импорт только для логов в файл.
//Для Андроид воспользовался бы удобным LogCat.
import java.io.PrintWriter
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    log( "App started.")

    val myInputMethod = MyInputMethod()

    val ip1 = myInputMethod.getIPAddress()
    val ip2 = myInputMethod.getIPAddress()

    printRangeBetweenIP(ip1,ip2)
}


//Выводим список адресов между двумя IP
fun printRangeBetweenIP (_ip1:AddressIP, _ip2:AddressIP) {
    var ip1 = _ip1
    var ip2 = _ip2
    //Меняем местами, если первый больше второго
    if (ip1 > ip2) {
        val tmp = ip2
        ip2 = ip1
        ip1 = tmp
    }

    for (i0 in ip1.address[0]..ip2.address[0]) {
        for (i1 in ip1.address[1]..ip2.address[1]) {
            for (i2 in ip1.address[2]..ip2.address[2]) {
                for (i3 in ip1.address[3]+1..ip2.address[3]-1) { //+ и - 1 нужны, чтобы не отдавать сами адреса.
                    println(AddressIP(mutableListOf(i0, i1, i2, i3)).toString())
                }
                ip1.address[3] = 0
            }
            ip1.address[2] = 0
        }
        ip1.address[1] = 0
    }
}

fun log(text:String) {
    val printWriter = PrintWriter(FileWriter("application.log", true))
    printWriter.println(LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + ": " + text)
    printWriter.close()
}

