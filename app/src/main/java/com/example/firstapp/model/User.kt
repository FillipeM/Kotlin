package com.example.firstapp.model

class User {

    constructor(codUsuario: Int, name: String, login: String, pwd: String, deleted: Boolean) {
        this.codUsuario = codUsuario
        this.name = name
        this.login = login
        this.pwd = pwd
        this.deleted = deleted
    }

    constructor()

    var codUsuario: Int = 0
    var name: String = ""
    var login: String = ""
    var pwd: String = ""
    var deleted: Boolean = false

}