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

    override fun toString(): String {
        return (this.address[0].toString()+"."+this.address[1].toString()+"."+this.address[2].toString()+"."+this.address[3].toString())
    }
}