package test.test.inv.source.local.repository.customer

import test.test.inv.source.local.data.CustomerLocal

class CustomerRepository(
    private val dao: CustomerDao
) : ICustomerRepository {
    override suspend fun saveCustomer(customer: CustomerLocal) {
        dao.saveCustomer(customer)
    }
}