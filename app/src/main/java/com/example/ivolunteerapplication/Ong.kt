package com.example.ivolunteerapplication

import java.io.Serializable
import com.google.gson.GsonBuilder


class Ong : Serializable {

    var id= ""
    var name = ""
    var username = ""
    var email = ""
    var responsavel = ""
    var data_abertura = ""
    var phone = ""
    var address = ""
    var city = ""
    var state = ""
    var descricao = ""
    var cnpj = ""
    var area_atuacao = ""
    var password = ""
    var status = "true"


    override fun toString(): String {
        return "Ong(nome='$name')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}