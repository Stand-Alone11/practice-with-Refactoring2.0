import dto.Performance
import dto.Play
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.floor

class InvoiceMaker {
    fun statement(invoice: HashMap<String, Any>, plays: HashMap<String, Play>): String {
        fun playFor(aPerformance: Performance): Play {
            return plays[aPerformance.playId]!!
        }

        // 위치 이동
        fun amountFor(aPerformance: Performance): Int {
            var result = 0
            when(aPerformance.play.type) {
                "tragedy" -> {
                    result = 40000;
                    if(aPerformance.audience > 30) {
                        result += 1000 * (aPerformance.audience - 30)
                    }
                }
                "comedy" -> {
                    result = 30000
                    if(aPerformance.audience > 20) {
                        result += 10000 + 500 * (aPerformance.audience - 20)
                    }
                    result += 300 * aPerformance.audience
                }
                else -> throw Exception("알 수 없는 장르: ${aPerformance.play.type}")
            }
            return result
        }

        fun enrichPerformance(aPerformance: Performance): Performance {
            val result = aPerformance.copy()
            result.play = playFor(result) // aPerformance -> result
            result.amount = amountFor(result) // bug fix: play가 할당된 result를 넘겨줘야 함 aPerformance -> result
            return result
        }

        val statementData = StatementData()
        statementData.customer = invoice["customer"] as String
        statementData.performances = (invoice["performances"] as List<Performance>).map{ enrichPerformance(it) }

        return renderPlainText(statementData, plays)
    }

    class StatementData() {
        lateinit var customer: String
        lateinit var performances: List<Performance>
    }

    fun renderPlainText(statementData: StatementData, plays: HashMap<String, Play>): String {

        fun volumeCreditsFor(aPerformance: Performance): Int {
            var result = 0
            result += Math.max(aPerformance.audience - 30 , 0)
            if("comedy" == aPerformance.play.type) result += floor((aPerformance.audience / 5).toDouble()).toInt()
            return result
        }

        fun usd(aNumber: Double): String {
            return NumberFormat.getCurrencyInstance().apply {
                maximumFractionDigits = 2
                minimumFractionDigits = 2
                currency = Currency.getInstance(Locale.US)
            }.format(aNumber / 100)
        }

        fun totalVolumeCredits(): Int {
            var result = 0
            for(perf in statementData.performances) {
                result += volumeCreditsFor(perf)
            }
            return result
        }

        fun totalAmount(): Int {
            var result = 0
            for(perf in statementData.performances) {
                result += perf.amount // amountFor() -> Performance.amount
            }
            return result
        }

        var result = "청구 내역 (고객명: ${statementData.customer})\n"
        for(perf in statementData.performances) {
            // print invoices
            result += " ${perf.play.name}: ${usd(perf.amount.toDouble())} (${perf.audience}석)\n" // amountFor() -> Performance.amount
        }

        result += "총액: ${usd(totalAmount().toDouble())}\n"
        result += "적립 포인트: ${totalVolumeCredits()}점\n"
        return result
    }
}