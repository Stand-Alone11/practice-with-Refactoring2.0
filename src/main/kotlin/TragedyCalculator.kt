import dto.Performance
import dto.Play

class TragedyCalculator(aPerformance: Performance, aPlay: Play): PerformanceCalculator(aPerformance, aPlay) {
    override fun amount(): Int {
        var result = 40000
        if(performance.audience > 30) {
            result += 1000 * (performance.audience - 30)
        }
        return result
    }

    override val amount = amount()
}