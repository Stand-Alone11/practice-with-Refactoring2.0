import dto.Performance
import dto.Play
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val invoice = getInvoice()
    val plays = getPlays()
    val invoiceMaker = InvoiceMaker()
    print(invoiceMaker.statement(invoice, plays))
}

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