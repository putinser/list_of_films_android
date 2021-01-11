package com.vmeste.app.common.errors.excetpions

class WrongFieldException : Exception {

    var resourceId: Int? = null
        private set

    var messageString: String? = null
        private set


    var additionalData: List<Any>? = null

    constructor(resourceId: Int) : super() {
        this.resourceId = resourceId
    }

    constructor(resourceId: Int, list: List<Any>) : super() {
        additionalData = ArrayList(list)
        this.resourceId = resourceId
    }

    constructor(message: String) : super(message) {
        messageString = message
    }
}
