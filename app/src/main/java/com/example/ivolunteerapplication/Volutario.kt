package com.example.ivolunteerapplication

import java.io.Serializable
import com.google.gson.GsonBuilder


class Voluntario : Serializable {

    var id= ""
    var name = ""
    var username = ""
    var email = ""
    var birth = ""
    var profession_id = ""
    var phone = ""
    var address = ""
    var city = ""
    var state = ""
    var assistencia_social = ""
    var cultura = ""
    var saude = ""
    var dev_defesa_direito = ""
    var habitacao = ""
    var educacao_pesquisa = ""
    var meio_ambiente = ""


    override fun toString(): String {
        return "Voluntario(nome='$name')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}