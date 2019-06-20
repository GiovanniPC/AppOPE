package com.example.ivolunteerapplication

import android.util.Log
import okhttp3.*
import java.io.IOException

object HttpHelper {

    private val TAG = "ivolunteer-rest-api"
    private val LOG_ON = true
    val JSON = MediaType.parse("application/json; charset=utf-8")

    var client = OkHttpClient()

    // GET
    fun get(url:String): String {
        Log.d(TAG, "HttpHelper.get: $url")
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    fun getv(url:String): String {
        Log.d(TAG, "HttpHelper.get: $url")
        var tokenurl: String = Prefs.getString("token")
        val request = Request.Builder().url(url).addHeader("Authorization", "Bearer " + tokenurl).get().build()
        return getJson(request)
    }

    // POST JSON
    fun post(url: String, json: String): String {
        Log.d(TAG, "HttpHelper.post: $url > $json")
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    // Lê resposta em formato JSON
    private fun getJson(request: Request?): String {
        val response = client.newCall(request).execute()
        val body = response.body()
        if (body != null) {
            val json = body.string()

            Log.d(TAG, "  <<<< : $json")

            return json
        }
        throw IOException("Erro na requisição")
    }
}