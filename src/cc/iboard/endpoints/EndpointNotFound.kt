package cc.iboard.endpoints

class EndpointNotFound(endpoint: String, e: Exception) :
        Exception("Endpoint not found: " + endpoint +
        "Error: " + e.message) {

    companion object {
        private val serialVersionUID = 1L
    }
}
