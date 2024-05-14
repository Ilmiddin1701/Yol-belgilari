package com.ilmiddin1701.yolharakatiqoidalari.db

import com.ilmiddin1701.yolharakatiqoidalari.models.SymbolData

interface DbInterface {
    fun addSymbol(symbolData: SymbolData)
    fun showSymbols(): List<SymbolData>
    fun editSymbol(symbolData: SymbolData)
    fun deleteSymbol(symbolData: SymbolData)
}