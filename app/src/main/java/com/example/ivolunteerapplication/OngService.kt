package com.example.ivolunteerapplication

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OngService {


    val host = "https://ivolunteer-rest-api.herokuapp.com"
    val TAG = "ivolunteer-rest-api"

    fun getOngSaude(context: Context): List<Ong> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/saude"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Ong>()
        }
    }

    fun getOngCultura(context: Context): List<Ong> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/cultura"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Ong>()
        }
    }

    fun getOngMeioAmbiente(context: Context): List<Ong> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/meio-ambiente"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Ong>()
        }
    }

    fun getOngDefesadDireito(context: Context): List<Ong> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/defesa-direito"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Ong>()
        }
    }

    fun getOngEducacaoPesquisa(context: Context): List<Ong> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/educacao-pesquisa"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Ong>()
        }
    }

    fun getOngAssistenciaSocial(context: Context): List<Ong> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/assistencia-social"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Ong>()
        }
    }

    fun getOngHabitacao(context: Context): List<Ong> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/habitacao"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Ong>()
        }
    }

    fun save(ong: Ong): Response {
        val json = HttpHelper.post("$host/signup/ong", ong.toJson())
        return parserJson<Response>(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}