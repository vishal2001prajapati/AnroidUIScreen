package com.example.anroiduiscreen

import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

fun requestApiCall(jsonObject: JSONObject, url: URL, requestType: String, codeResponse: Int, responseCallback: ResponseCallback) {
    with(url.openConnection() as HttpURLConnection) {
        requestMethod = requestType
        setRequestProperty("Content-Type", "application/json")
        val outputStreamWriter = OutputStreamWriter(outputStream)
        outputStreamWriter.apply {
            write(jsonObject.toString())
            flush()
        }
        Log.d("URL", "$url")
        Log.d("jsonObject", "$jsonObject")

        if (responseCode == codeResponse) {
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                responseCallback.onSuccess(responseMessage)
                Log.d("responseCodeValue", "$responseCode")
            }
        } else {
            responseCallback.onFailure(responseCode, responseMessage)
            Log.d("responseCodeValue", "$responseCode")
        }
    }
}