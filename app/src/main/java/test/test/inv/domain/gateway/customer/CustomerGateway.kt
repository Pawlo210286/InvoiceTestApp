package test.test.inv.domain.gateway.customer

import test.test.inv.domain.data.Customer
import test.test.inv.domain.data.mapper.toLocal
import test.test.inv.source.local.repository.customer.ICustomerRepository

class CustomerGateway(
    private val local: ICustomerRepository
) : ICustomerGateway {
    override suspend fun saveCustomer(customer: Customer) {
        local.saveCustomer(customer.toLocal())
    }
}