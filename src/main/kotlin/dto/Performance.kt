package dto

data class Performance(val playId: String, var audience: Int) {
    lateinit var play: Play // play 추가
}
