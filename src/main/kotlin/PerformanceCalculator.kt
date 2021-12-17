import dto.Performance
import dto.Play
import kotlin.math.floor

open class PerformanceCalculator(val performance: Performance, val play: Play) {
    open fun amount(): Int {
        throw Exception("하위 클래스에서 처리해야함")
    }

    open fun volumeCredits(): Int {
        return Math.max(performance.audience - 30 , 0)
    }

    open val amount = amount()
    val volumeCredits = volumeCredits()
}