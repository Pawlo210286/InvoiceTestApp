package test.test.inv.domain.usecase.customer

import test.test.inv.domain.data.Customer
import test.test.inv.domain.gateway.customer.ICustomerGateway

class CustomerInteractor(
    private val customerGateway: ICustomerGateway
) : CustomerUseCase {

    override suspend fun saveCustomer(customer: Customer) {
        customerGateway.saveCustomer(customer)
    }
}