package com.example.ivolunteerapplication

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object VoluntarioService {


    val host = "https://ivolunteer-rest-api.herokuapp.com"
    val TAG1 = "ivolunteer-rest-api3"

    fun getOngSaude(context: Context): List<Voluntario> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/saude"
            val json = HttpHelper.getv(url)
            if(json.contains("Error")) {
                val teste = ArrayList<Voluntario>();
                return teste
            }
            return parserJson(json)
        } else {
            return ArrayList<Voluntario>()
        }
    }

    fun getOngCultura(context: Context): List<Voluntario> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/cultura"
            val json = HttpHelper.getv(url)
            if(json.contains("Error")) {
                val teste = ArrayList<Voluntario>();
                return teste
            }
            return parserJson(json)
        } else {
            return ArrayList<Voluntario>()
        }
    }

    fun getOngMeioAmbiente(context: Context): List<Voluntario> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/meio-ambiente"
            val json = HttpHelper.getv(url)
            if(json.contains("Error")) {
                val teste = ArrayList<Voluntario>();
                return teste
            }
            return parserJson(json)
        } else {
            return ArrayList<Voluntario>()
        }
    }

    fun getOngDefesadDireito(context: Context): List<Voluntario> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/defesa-direito"
            val json = HttpHelper.getv(url)
            if(json.contains("Error")) {
                val teste = ArrayList<Voluntario>();
                return teste
            }
            return parserJson(json)
        } else {
            return ArrayList<Voluntario>()
        }
    }

    fun getOngEducacaoPesquisa(context: Context): List<Voluntario> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/educacao-pesquisa"
            val json = HttpHelper.getv(url)
            if(json.contains("Error")) {
                val teste = ArrayList<Voluntario>();
                return teste
            }
            return parserJson(json)
        } else {
            return ArrayList<Voluntario>()
        }
    }

    fun getOngAssistenciaSocial(context: Context): List<Voluntario> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/assistencia-social"
            val json = HttpHelper.getv(url)
            if(json.contains("Error")) {
                val teste = ArrayList<Voluntario>();
                return teste
            }
            return parserJson(json)
        } else {
            return ArrayList<Voluntario>()
        }
    }

    fun getOngHabitacao(context: Context): List<Voluntario> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/habitacao"
            val json = HttpHelper.getv(url)
            if(json.contains("Error")) {
                val teste = ArrayList<Voluntario>();
                return teste
            }
            return parserJson(json)
        } else {
            return ArrayList<Voluntario>()
        }
    }


    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}