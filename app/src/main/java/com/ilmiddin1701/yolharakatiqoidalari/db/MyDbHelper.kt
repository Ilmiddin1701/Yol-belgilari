package com.ilmiddin1701.yolharakatiqoidalari.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ilmiddin1701.yolharakatiqoidalari.models.SymbolData

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, "db_name", null, 1), DbInterface {
    override fun onCreate(db: SQLiteDatabase?) {
        val symbolQuery =
            "create table symbol_table(id integer not null primary key autoincrement unique, image text not null, name text not null, about text not null, is_checked integer not null, is_liked integer not null)"
        db?.execSQL(symbolQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addSymbol(symbolData: SymbolData) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("image", symbolData.image)
        contentValues.put("name", symbolData.name)
        contentValues.put("about", symbolData.about)
        contentValues.put("is_checked", symbolData.isChecked)
        contentValues.put("is_liked", symbolData.isLiked)
        database.insert("symbol_table", null, contentValues)
        database.close()
    }

    override fun showSymbols(): List<SymbolData> {
        val list = ArrayList<SymbolData>()
        val database = this.readableDatabase
        val query = "select * from symbol_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    SymbolData(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                    )
                )
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editSymbol(symbolData: SymbolData) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("image", symbolData.image)
        contentValues.put("name", symbolData.name)
        contentValues.put("about", symbolData.about)
        contentValues.put("is_checked", symbolData.isChecked)
        contentValues.put("is_liked", symbolData.isLiked)
        database.update("symbol_table", contentValues, "id = ?", arrayOf(symbolData.id.toString()))
    }

    override fun deleteSymbol(symbolData: SymbolData) {
        val database = this.writableDatabase
        database.delete("symbol_table", "id = ?", arrayOf(symbolData.id.toString()))
    }
}