package com.example.firstapp.model

class Produto {
    var codProduto = 0
    var descProduto = ""
    var categoria: Categoria? = Categoria()
    var img: ByteArray? = null

    constructor()

    constructor(codProduto: Int, descProduto: String, categoria: Categoria?, img: ByteArray?){
        this.codProduto = codProduto
        this.descProduto = descProduto
        this.categoria = categoria
        this.img = img
    }
}