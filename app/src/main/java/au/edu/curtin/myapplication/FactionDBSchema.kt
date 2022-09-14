package au.edu.curtin.myapplication

import android.provider.BaseColumns

object FactionDBSchema {

    const val SQL_CREATE_TABLE_QUERY =
        "CREATE TABLE ${FactionTableInfo.TABLE_NAME} (" +
                "${FactionTableInfo.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${FactionTableInfo.COLUMN_NAME} TEXT," +
                "${FactionTableInfo.COLUMN_STRENGTH} INTEGER," +
                "${FactionTableInfo.COLUMN_RELATIONSHIP} INTEGER)"

    const val SQL_DELETE_TABLE_QUERY = "DROP TABLE IF EXIST ${FactionTableInfo.TABLE_NAME}"

    object FactionTableInfo: BaseColumns {
        const val TABLE_NAME = "factions"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_STRENGTH = "strength"
        const val COLUMN_RELATIONSHIP = "relationship"
    }
}