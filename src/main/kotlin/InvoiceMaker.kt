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

        fun amountFor(aPerformance: Performance): Int {
            var result = 0 // 명확한 이름으로 변경
            when(playFor(aPerformance).type) { // playFor() 함수 인라인
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
                else -> throw Exception("알 수 없는 장르: ${playFor(aPerformance).type}")
            }
            return result
        }

        var totalAmount = 0
        var volumeCredits = 0
        var result = "청구 내역 (고객명: ${invoice["customer"]})\n"

        val format = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            currency = Currency.getInstance(Locale.US)
        }

        // amountFor()로 구한 thisAmount는 이후에 값이 바뀌지 않으므로 thisAmount를 사용하는 부분에 amountFor() 인라인
        for(perf in invoice["performances"] as List<Performance>) {
            //val play = playFor(perf)    playFor()함수 인라인으로 삭제
            // add point
            volumeCredits += Math.max(perf.audience - 30 , 0)
            // add 5points per an audience of Comedy
            if("comedy" == playFor(perf).type) volumeCredits += floor((perf.audience / 5).toDouble()).toInt() // playFor() 함수 인라인

            // print invoices
            result += " ${playFor(perf).name}: ${format.format(amountFor(perf).toDouble() / 100)} (${perf.audience}석)\n" // playFor(), amountFor() 함수 인라인
            totalAmount += amountFor(perf) // amountFor() 함수 인라인
        }
        result += "총액: ${format.format(totalAmount.toDouble() / 100)}\n"
        result += "적립 포인트: ${volumeCredits}점\n"
        return result
    }
}