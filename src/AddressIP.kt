class AddressIP(var address:MutableList<Int>) : Comparable<AddressIP> {
    //MutableList чтобы изменять отдельные значения.
    //Замечу, что AddressIP не проверяет корректность подаваемых данных - это не его дело.
    override fun compareTo(other: AddressIP): Int {
        for (i in 0..3) {
            if (this.address[i]>other.address[i]) return 1
            else if (this.address[i]<other.address[i]) return -1
        }
        return 0
    }

    //Родной метод equals для разных объектов, но с одним ip выдаст false, что логично.
    fun isEqual(other: AddressIP):Boolean {
        return this.compareTo(other)==0
    }

    override fun toString(): String {
        return (this.address[0].toString()+"."+this.address[1].toString()+"."+this.address[2].toString()+"."+this.address[3].toString())
    }

    fun getNextIP():AddressIP {
        var i = mutableListOf(this.address[0], this.address[1], this.address[2], this.address[3])
        //Если это максимальный адрес - выдаем нулевой.
        if (i[0]==MAX_VALUE_IN_IP && i[1]==MAX_VALUE_IN_IP && i[2]==MAX_VALUE_IN_IP && i[3]==MAX_VALUE_IN_IP) return AddressIP(mutableListOf(0,0,0,0))

        if (i[1] == MAX_VALUE_IN_IP && i[2] == MAX_VALUE_IN_IP && i[3] == MAX_VALUE_IN_IP) {
            i[0]++
            i[1] = 0
            i[2] = 0
            i[3] = 0
        } else if (i[2] == MAX_VALUE_IN_IP && i[3] == MAX_VALUE_IN_IP) {
            i[1]++
            i[2] = 0
            i[3] = 0
        } else if (i[3] == MAX_VALUE_IN_IP) {
            i[2]++
            i[3] = 0
        } else i[3]++

        return  AddressIP(mutableListOf(i[0], i[1], i[2], i[3]))
    }
}