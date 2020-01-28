package test.test.inv.domain.data

enum class ErrorType {
    DENIED_GPS {
        override fun toString(): String = "GPS"
    },
    DENIED_IMEI {
        override fun toString(): String = "IMEI"
    },
    DEPRECATED_OS,
    APP_ERROR,
    SERVER_ERROR,
    ENABLE_BATTERY_SAVE
}