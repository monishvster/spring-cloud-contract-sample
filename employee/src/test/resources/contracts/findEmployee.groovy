package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Find employee with employeeID = 1")
    request {
        method 'GET'
        url '/employee/1'
    }
    response {
        status 200
        body("""
              {
                "id": "1",
                "fname": "Jane",
                "lname": "Doe",
                "salary": "123000.00",
                "gender": "M"
              }
              """)
        headers {
            contentType(applicationJson())
        }
    }

}