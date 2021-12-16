import dto.Performance
import dto.Play
import io.kotlintest.shouldBe
import io.kotlintest.specs.AnnotationSpec

class MainTest: AnnotationSpec(){
    lateinit var invoice: HashMap<String, Any>
    lateinit var plays: HashMap<String, Play>

    @Before
    fun init() {
        fun getInvoice() : HashMap<String, Any> {
            val invoice = HashMap<String, Any>()
            invoice["customer"] = "BicCo"
            invoice["performances"] = mutableListOf(Performance("hamlet", 55), Performance("as-like", 35), Performance("othello", 40))
            return invoice
        }

        fun getPlays() : HashMap<String, Play> {
            val plays = HashMap<String, Play>()
            plays["hamlet"] = Play("Hamlet", "tragedy")
            plays["as-like"] = Play("as-like", "comedy")
            plays["othello"] = Play("Othello", "tragedy")
            return plays
        }

        invoice = getInvoice()
        plays = getPlays()
    }


    @Test
    fun compareWithBeforeRefactoring() {
        val invoiceMaker = InvoiceMaker()
        val result = invoiceMaker.statement(invoice, plays)
        result shouldBe "청구 내역 (고객명: BicCo)\n Hamlet: US${'$'}650.00 (55석)\n as-like: US${'$'}580.00 (35석)\n Othello: US${'$'}500.00 (40석)\n총액: US${'$'}1,730.00\n적립 포인트: 47점\n"
    }

    @Test
    fun performanceCalculatorResult() {
        val result = InvoiceMaker().createStatementData(invoice, plays)
        result.toString() shouldBe "StatementData(totalAmount=173000, totalVolumeCredits=47, customer=BicCo)"
        //print(result)

    }
}