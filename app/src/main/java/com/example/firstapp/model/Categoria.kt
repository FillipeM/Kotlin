package com.example.firstapp.model

class Categoria {
    var codCategoria = 0
    var descCategoria = ""

    constructor()

    constructor(codCategoria: Int, descCategoria: String){
        this.codCategoria = codCategoria
        this.descCategoria = descCategoria
    }
}