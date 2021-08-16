package com.fpinkotlin.makingprogramssafer.listing06


data class Payment(val creditCard: CreditCard, val amount: Int) {
    fun combine(payment: Payment): Payment =
            if (creditCard == payment.creditCard)
                Payment(creditCard, amount + payment.amount)
            else
                throw IllegalStateException("Cards don't match.")

    companion object {
        fun groupByCard(payments: List<Payment>): List<Payment> =
                payments.groupBy { it.creditCard } // -> <Card, Payment>
                        .values // <Payment> [Payment(cc1, 5), Payment(cc1, 1), Payment(cc2, 5)]
                        .map { it.reduce(Payment::combine) }    // scan, and if payments has the same card, we combine them
                                                                // to one big payment
                                                                // [Payment(cc1, 6), Payment(cc2, 5)]
    }
}
