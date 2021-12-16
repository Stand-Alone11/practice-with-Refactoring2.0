package dto

data class StatementData(var totalAmount: Int = -1, var totalVolumeCredits: Int = -1) {
    lateinit var customer: String
    lateinit var performances: List<Performance>
}
