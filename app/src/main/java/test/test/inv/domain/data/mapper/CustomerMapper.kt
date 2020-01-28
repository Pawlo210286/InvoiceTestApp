package test.test.inv.domain.data.mapper

import test.test.inv.domain.data.Customer
import test.test.inv.source.local.data.CustomerLocal

fun Customer.toLocal(): CustomerLocal {
    return CustomerLocal(
        id = id,
        name = name
    )
}

fun CustomerLocal.toEntity(): Customer {
    return Customer(
        id = id,
        name = name
    )
}