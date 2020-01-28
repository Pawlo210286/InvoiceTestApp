package test.test.inv.domain.data

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import test.test.inv.R

enum class InvoiceState(@StringRes val title: Int, @ColorRes val color: Int) {
    LATE(R.string.late, R.color.state_late),
    OPEN(R.string.open, R.color.state_open),
    PAID(R.string.paid, R.color.state_paid)
}

fun fromString(state: String): InvoiceState {
    return when (state) {
        "LATE" -> InvoiceState.LATE
        "OPEN" -> InvoiceState.OPEN
        "PAID" -> InvoiceState.PAID
        else -> throw IllegalArgumentException()
    }
}