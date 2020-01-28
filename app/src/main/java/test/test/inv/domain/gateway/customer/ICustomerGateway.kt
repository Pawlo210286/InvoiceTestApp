package test.test.inv.domain.gateway.customer

import test.test.inv.domain.data.Customer

interface ICustomerGateway {
    suspend fun saveCustomer(customer: Customer)
}