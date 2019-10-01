class MyInputMethod {
    private fun  getString() : String {
        return ""
    }

    fun getIPAddress() : AddressIP {
        var ip : AddressIP?
        while (true) {
            ip = this.getString().getIPAddressOrNull()
            if (ip!=null) return ip
            println("Incorrect IP, need form XXXX.XXXX.XXXX.XXXX . Try again.")
        }
    }
}

fun String.isCorrectIP() : Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

//Можно было сделать без null, но тогда в теории мы могли бросать исключение
//По аналогии с String.toInt()
fun String.getIPAddressOrNull() : AddressIP? {
    return if (!this.isCorrectIP())
        null
    else {
        AddressIP(listOf())
    }
}