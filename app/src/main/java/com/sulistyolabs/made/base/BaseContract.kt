package com.sulistyolabs.made.base

interface BaseContract {

    fun showLoading()
    fun hideLoading()
    fun onFailed(message: String)
    fun onError(message: String)

}