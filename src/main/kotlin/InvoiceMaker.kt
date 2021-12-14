import dto.Performance
import dto.Play
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.floor

class InvoiceMaker {
    fun statement(invoice: HashMap<String, Any>, plays: HashMap<String, Play>): String {
        /**
         * 문제발생
         * 자바스크립트는 객체 생성 후 프로퍼티 할당이 가능하지만 코틀린은 불가능하다
         * 따라서 statementData를 임시 class로 만들어 진행한 뒤, 마지막에 data class로 변환할 예정
         */

        val statementData = StatementData()
        statementData.customer = invoice["customer"] as String
        statementData.performances = invoice["performances"] as List<Performance>
        return renderPlainText(statementData, plays)
    }

    class StatementData() {
        lateinit var customer: String
        lateinit var performances: List<Performance>
    }

    // invoice 매개변수를 없애기 위해 invoice의 정보를 statementData로 이동시키기
    fun renderPlainText(statementData: StatementData, plays: HashMap<String, Play>): String {  // invoice 삭제
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

        fun volumeCreditsFor(aPerformance: Performance): Int {
            var result = 0
            result += Math.max(aPerformance.audience - 30 , 0)
            if("comedy" == playFor(aPerformance).type) result += floor((aPerformance.audience / 5).toDouble()).toInt()
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
            for(perf in statementData.performances) { // invoice -> statementData
                result += volumeCreditsFor(perf)
            }
            return result
        }

        fun totalAmount(): Int {
            var result = 0
            for(perf in statementData.performances) {  // invoice -> statementData
                result += amountFor(perf)
            }
            return result
        }

        var result = "청구 내역 (고객명: ${statementData.customer})\n"
        for(perf in statementData.performances) {  // invoice -> statementData
            // print invoices
            result += " ${playFor(perf).name}: ${usd(amountFor(perf).toDouble())} (${perf.audience}석)\n"
        }

        result += "총액: ${usd(totalAmount().toDouble())}\n"
        result += "적립 포인트: ${totalVolumeCredits()}점\n"
        return result
    }
}