package test.test.inv.presentation.util

import java.text.SimpleDateFormat
import java.util.*


const val REQUEST_DATE_FORMAT = "yyyy-MM-dd"
const val DISPLAY_DATE_FORMAT = "MM/dd/yyyy"


fun String.fromRequestToDisplayDate(): String {
    return SimpleDateFormat(
        DISPLAY_DATE_FORMAT,
        Locale.getDefault()
    ).format(
        SimpleDateFormat(
            REQUEST_DATE_FORMAT,
            Locale.getDefault()
        ).parse(this)!!
    )
}


fun Date.toRequestFormat(): String {
    return SimpleDateFormat(REQUEST_DATE_FORMAT, Locale.getDefault()).format(this)
}