import dto.Performance
import dto.Play
import dto.StatementData
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.floor

class InvoiceMaker {
    fun statement(invoice: HashMap<String, Any>, plays: HashMap<String, Play>): String {
        return renderPlainText(createStatementData(invoice, plays))
    }

    fun createStatementData(invoice: HashMap<String, Any>, plays: HashMap<String, Play>): StatementData {
        fun playFor(aPerformance: Performance): Play {
            return plays[aPerformance.playId]!!
        }

        fun totalAmount(statementData: StatementData): Int {
            return statementData.performances.sumOf { it.amount }
        }

        fun totalVolumeCredits(statementData: StatementData): Int {
            return statementData.performances.sumOf { it.volumeCredit }
        }

        fun enrichPerformance(aPerformance: Performance): Performance {

            val calculator = PerformanceCalculator(aPerformance, playFor(aPerformance))
            val result = aPerformance.copy()
            result.play = calculator.play
            result.amount = calculator.amount
            result.volumeCredit = calculator.volumeCredits // volumeCreditsFore() -> calculator.volumeCredits
            return result
        }

        val statementData = StatementData()
        statementData.customer = invoice["customer"] as String
        statementData.performances = (invoice["performances"] as List<Performance>).map{ enrichPerformance(it) }
        statementData.totalAmount = totalAmount(statementData)
        statementData.totalVolumeCredits = totalVolumeCredits(statementData)
        return statementData
    }

    fun renderPlainText(statementData: StatementData): String {
        fun usd(aNumber: Double): String {
            return NumberFormat.getCurrencyInstance().apply {
                maximumFractionDigits = 2
                minimumFractionDigits = 2
                currency = Currency.getInstance(Locale.US)
            }.format(aNumber / 100)
        }

        var result = "청구 내역 (고객명: ${statementData.customer})\n"
        for(perf in statementData.performances) {
            // print invoices
            result += " ${perf.play.name}: ${usd(perf.amount.toDouble())} (${perf.audience}석)\n"
        }

        result += "총액: ${usd(statementData.totalAmount.toDouble())}\n"
        result += "적립 포인트: ${statementData.totalVolumeCredits}점\n"
        return result
    }
}