package com.github.dj0l33x.psp.acquirer

interface Acquirer {
    fun isEven(): Boolean

    fun processTransaction(transaction: AcquirerTransaction): AcquirerTransactionResult
}
