package com.example.anroiduiscreen

interface ResponseCallback {
    fun onSuccess(output: String)
    fun onFailure(responseCode: Int, output: String)
}