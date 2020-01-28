package test.test.inv.source.local.repository.customer

import test.test.inv.source.local.data.CustomerLocal

interface ICustomerRepository {
    suspend fun saveCustomer(customer: CustomerLocal)
}