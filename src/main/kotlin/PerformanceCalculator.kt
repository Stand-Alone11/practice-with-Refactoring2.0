import dto.Performance
import dto.Play
import kotlin.math.floor

open class PerformanceCalculator(val performance: Performance, val play: Play) {
    open fun amount(): Int {
        var result = 0
        when(play.type) {
            "tragedy" -> {
                result = 40000;
                if(performance.audience > 30) {
                    result += 1000 * (performance.audience - 30)
                }
            }
            "comedy" -> {
                result = 30000
                if(performance.audience > 20) {
                    result += 10000 + 500 * (performance.audience - 20)
                }
                result += 300 * performance.audience
            }
            else -> throw Exception("알 수 없는 장르: ${play.type}")
        }
        return result
    }

    // slide volumeCredits() from InvoiceMaker.createStatementData()
    open fun volumeCredits(): Int {
        var result = 0
        result += Math.max(performance.audience - 30 , 0)
        if("comedy" == play.type) result += floor((performance.audience / 5).toDouble()).toInt()
        return result
    }

    open val amount = amount()
    val volumeCredits = volumeCredits()
}