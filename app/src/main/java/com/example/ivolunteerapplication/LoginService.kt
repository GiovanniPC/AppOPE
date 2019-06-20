package com.example.ivolunteerapplication

object LoginService {

    val host = "https://ivolunteer-rest-api.herokuapp.com"

    fun login(ong: OngLogin): String {

        return HttpHelper.post("$host/login/ong", ong.toJson())

    }

    fun login2(ong: OngLogin): String {

        return HttpHelper.post("$host/login/volunteer", ong.toJson())

    }

}