package com.vmeste.app.common.errors.excetpions

class ServiceException(messageId: Int) : Exception() {

    var messageId: Int

    init {
        this.messageId = messageId

    }


}
