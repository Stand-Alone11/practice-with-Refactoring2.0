import dto.Performance
import dto.Play

class PerformanceCalculator(val performance: Performance, val play: Play) {
    fun amount(): Int {
        var result = 0
        when(play.type) { // performance.play -> play
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
            else -> throw Exception("알 수 없는 장르: ${play.type}") // performance.play -> play
        }
        return result
    }

    val amount = amount()
}