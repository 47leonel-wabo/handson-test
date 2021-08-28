import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return a student")
    request {
        method GET()
        url "/api/student/1"
    }
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(id: 1, name: "assa", grade: 17)
    }
}