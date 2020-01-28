package test.test.inv.domain.usecase.customer

import test.test.inv.domain.data.Customer

interface CustomerUseCase {
    suspend fun saveCustomer(customer: Customer)
}