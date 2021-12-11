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

        var totalAmount = 0;
        var volumeCredits = 0;
        var result = "청구 내역 (고객명: ${invoice["customer"]})\n"

        val format = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            currency = Currency.getInstance(Locale.US)
        }

        for(perf in invoice["performances"] as List<Performance>) {
            //val play = playFor(perf)    playFor()함수 인라인으로 삭제
            var thisAmount = amountFor(perf)// playFor() 함수 인라인

            // add point
            volumeCredits += Math.max(perf.audience - 30 , 0)
            // add 5points per an audience of Comedy
            if("comedy" == playFor(perf).type) volumeCredits += floor((perf.audience / 5).toDouble()).toInt() // playFor() 함수 인라인

            // print invoices
            result += " ${playFor(perf).name}: ${format.format(thisAmount.toDouble() / 100)} (${perf.audience}석)\n" // playFor() 함수 인라인
            totalAmount += thisAmount
        }
        result += "총액: ${format.format(totalAmount.toDouble() / 100)}\n"
        result += "적립 포인트: ${volumeCredits}점\n"
        return result
    }
}