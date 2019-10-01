import java.io.InputStream
import java.io.PrintStream
import java.util.*

//
//open class StringAsker(input: InputStream, private val out: PrintStream) {
//     var scanner: Scanner = Scanner(input)
//     var output: PrintStream = PrintStream(out)
//
//    fun ask(message: String): String {
//        output.println(message)
//        return scanner.next()
//    }
//}

class MyInputMethod {
    fun  getString() : String {
        val scan = Scanner(System.`in`)
        val txt = scan.nextLine()
        log("User enter $txt")
        return txt
    }

    fun getIPAddress() : AddressIP {
        var ip : AddressIP?
        while (true) {
            print("Enter correct ip-address : ")
            ip = this.getString().getIPAddressOrNull()
            if (ip!=null) return ip
            println("Incorrect IP, need form XXXX.XXXX.XXXX.XXXX . Try again.")
        }
    }
}

fun String.isCorrectIP() : Boolean {
    val points = MutableList(5) {0}
    points[0] = -1 //Первая точка - до начала строки
    points[4] = this.length //Последняя точка - это конец строки
    val numbers = MutableList<Int?>(4) {-1}

    for (i in 1..4) {
        if (i!=4) points[i] = this.indexOf(".", points[i - 1] + 1) //Для последней "точки" мы уже задали значение
        if (points[i]==-1) return false
        numbers[i-1] = this.substring(points[i-1]+1,points[i]).toIntOrNull()
        if (numbers[i-1]==null ) return false
        else //Дальше мы точно знаем, что не null
            if (numbers[i-1]!! <0  || numbers[i-1]!! >255) return false
    }
    return true
}

//Можно было сделать без null, но тогда в теории мы могли бросать исключение
//По аналогии с String.toInt()
fun String.getIPAddressOrNull() : AddressIP? {
    return if (!this.isCorrectIP())
        null
    else {
        val points = MutableList(5) {0}
        points[0] = -1 //Первая точка - до начала строки
        points[4] = this.length //Последняя точка - это конец строки
        val numbers = MutableList(4) {-1}

        for (i in 1..4) {
            if (i!=4) points[i] = this.indexOf(".", points[i - 1] + 1) //Для последней "точки" мы уже задали значение
            numbers[i-1] = this.substring(points[i-1]+1,points[i]).toInt()
        }
        AddressIP(numbers)
    }
}