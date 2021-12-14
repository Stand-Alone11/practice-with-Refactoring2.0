package dto

data class Performance(val playId: String, var audience: Int) {
    lateinit var play: Play // play 추가
    var amount: Int = -1 // amount 추가
    var volumeCredit: Int = -1 // volumeCredit 추가
}
