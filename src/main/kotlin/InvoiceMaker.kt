import dto.Performance
import dto.Play
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.floor

class InvoiceMaker {
    fun statement(invoice: HashMap<String, Any>, plays: HashMap<String, Play>): String {
        /**
         * 임시 변수를 질의 함수로 바꾸기
         * play는 aPerformance 변수로 구할 수 있으므로 매개변수로 전달할 필요 없음
         * play -> playFor(aPerformance)
         */
        fun playFor(aPerformance: Performance): Play {
            return plays[aPerformance.playId]!!
        }

        fun amountFor(aPerformance: Performance, play: Play): Int {
            var result = 0 // 명확한 이름으로 변경
            when(play.type) {
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
                else -> throw Exception("알 수 없는 장르: ${play.type}")
            }
            return result
        }

        var totalAmount = 0;
        var volumeCredits = 0;
        var result = "청구 내역 (고객명: ${invoice["customer"]})\n"

        val format = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            currency = Currency.getInstance(Locale.US)
        }

        for(perf in invoice["performances"] as List<Performance>) {
            val play = playFor(perf) // 질의 함수 사용
            var thisAmount = amountFor(perf, play)

            // add point
            volumeCredits += Math.max(perf.audience - 30 , 0)
            // add 5points per an audience of Comedy
            if("comedy" == play.type) volumeCredits += floor((perf.audience / 5).toDouble()).toInt()

            // print invoices
            result += " ${play.name}: ${format.format(thisAmount.toDouble() / 100)} (${perf.audience}석)\n"
            totalAmount += thisAmount
        }
        result += "총액: ${format.format(totalAmount.toDouble() / 100)}\n"
        result += "적립 포인트: ${volumeCredits}점\n"
        return result
    }
}