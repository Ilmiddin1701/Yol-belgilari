package com.ilmiddin1701.yolharakatiqoidalari.models

class SymbolData {
    var id: Int? = null
    var image: String? = null
    var name: String? = null
    var about: String? = null
    var isChecked: Int? = null
    var isLiked: Int? = null

    constructor(id: Int?, image: String?, name: String?, about: String?, isChecked: Int?, isLiked: Int?) {
        this.id = id
        this.image = image
        this.name = name
        this.about = about
        this.isChecked = isChecked
        this.isLiked = isLiked
    }

    constructor(image: String?, name: String?, about: String?, isChecked: Int?, isLiked: Int?) {
        this.image = image
        this.name = name
        this.about = about
        this.isChecked = isChecked
        this.isLiked = isLiked
    }
}