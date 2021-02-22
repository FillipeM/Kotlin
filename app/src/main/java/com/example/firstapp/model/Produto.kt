package com.example.firstapp.model

class Produto {
    var codProduto = 0
    var descProduto = ""
    var categoria: Categoria? = Categoria()

    constructor()

    constructor(codProduto: Int, descProduto: String, categoria: Categoria){
        this.codProduto = codProduto
        this.descProduto = descProduto
        this.categoria = categoria
    }
}