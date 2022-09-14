package au.edu.curtin.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FactionDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "factions.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "factions"
        const val ID = "id"
        const val NAME = "name"
        const val STRENGTH = "strength"
        const val RELATIONSHIP = "relationship"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createDB = ("CREATE TABLE " + TABLE_NAME + "("
                + ID + "INTEGER PRIMARY KEY" + NAME + "TEXT,"
                + STRENGTH + "INTEGER," + RELATIONSHIP + "INTEGER" + ")")
        db?.execSQL(createDB)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropDB = FactionDBSchema.SQL_DELETE_TABLE_QUERY
        db!!.execSQL(dropDB)
        onCreate(db)
    }

    fun insertFaction(faction: Faction): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, faction.id)
        contentValues.put(NAME, faction.name)
        contentValues.put(STRENGTH, faction.strength)
        contentValues.put(RELATIONSHIP, faction.relationship)

        val success = db.insert(FactionDBSchema.FactionTableInfo.TABLE_NAME, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllFactions(): ArrayList<Faction>{
        val factionList: ArrayList<Faction> = ArrayList()
        val selectQuery = "SELECT * FROM ${FactionDBSchema.FactionTableInfo.TABLE_NAME}"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL((selectQuery))
            return ArrayList()
        }

        var id: Int
        var name: String
        var strength: Int
        var relationship: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                strength = cursor.getInt(cursor.getColumnIndex("strength"))
                relationship = cursor.getInt(cursor.getColumnIndex("relationship"))

                val faction = Faction(id,name,strength,relationship)
                factionList.add(faction)
            }while (cursor.moveToNext())
        }
        return factionList
    }

    fun updateFaction(faction: Faction): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, faction.id)
        contentValues.put(NAME, faction.name)
        contentValues.put(STRENGTH, faction.strength)
        contentValues.put(RELATIONSHIP, faction.relationship)

        val success = db.update(FactionDBSchema.FactionTableInfo.TABLE_NAME, contentValues, "id=" + faction.id, null)
        db.close()
        return success
    }

    fun deleteFaction(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(FactionDBSchema.FactionTableInfo.TABLE_NAME, "id="+id, null)
        db.close()
        return success
    }

}