package dto

data class StatementData(var totalAmount: Int = -1, var totalVolumeCredits: Int = -1) {
    lateinit var customer: String
    lateinit var performances: List<Performance>

    override fun toString(): String {
        return "StatementData(totalAmount=$totalAmount, totalVolumeCredits=$totalVolumeCredits, customer=$customer)"
    }
}
