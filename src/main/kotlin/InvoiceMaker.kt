import dto.Performance
import dto.Play
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.floor

class InvoiceMaker {
    fun statement(invoice: HashMap<String, Any>, plays: HashMap<String, Play>): String {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = "청구 내역 (고객명: ${invoice["customer"]})\n"

        val format = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            currency = Currency.getInstance(Locale.US)
        }

        for(perf in invoice["performances"] as List<Performance>) {
            val play = plays[perf.playId]
            var thisAmount = 0

            /**
             * when문 함수 추출 하기
             * perf, play, thiaAmount 변수들은 추출된 함수에서 사용 불가
             * perf, play는 사용하지만 값이 변하지 않아 매개변수로 전달
             * thisAmount는 함수 내부에서 변경, 이런 변수는 신중하게 다뤄야함
             */
            when(play!!.type) {
                "tragedy" -> {
                    thisAmount = 40000;
                    if(perf.audience > 30) {
                        thisAmount += 1000 * (perf.audience - 30)
                    }
                }
                "comedy" -> {
                    thisAmount = 30000
                    if(perf.audience > 20) {
                        thisAmount += 10000 + 500 * (perf.audience - 20)
                    }
                    thisAmount += 300 * perf.audience
                }
                else -> throw Exception("알 수 없는 장르: ${play.type}")
            }
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