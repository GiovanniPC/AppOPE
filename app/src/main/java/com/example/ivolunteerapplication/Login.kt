package com.example.ivolunteerapplication

import java.io.Serializable
import com.google.gson.GsonBuilder


class OngLogin : Serializable {

    var username = ""
    var password = ""

    override fun toString(): String {
        return "Ong(nome='$username')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}