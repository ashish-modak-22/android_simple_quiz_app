package com.example.quizapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "quiz_history.db"
        private const val DATABASE_VERSION = 1

        // Creating the table and column names of the database file
        const val TABLE_NAME = "score"
        const val COLUMN_ID = "id"
        const val COLUMN_SCORE = "score"
    }

    // This function will get called when the database is first created
    override fun onCreate(db: SQLiteDatabase?) {

        // Creating the 'score' table
        val createTable = """
            CREATE TABLE $TABLE_NAME(
               $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
               $COLUMN_SCORE TEXT NOT NULL
            )
        """.trimIndent()

        db?.execSQL(createTable)  // Start executing the sql query
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    // Data insertion
    fun insertScore(score: String): Boolean {
        val db = writableDatabase             // Open the database in write mode

        val values = ContentValues().apply {
            put(COLUMN_SCORE, score)
        }

        val success = db.insert(TABLE_NAME, null, values)
        db.close()

        return success != -1L
    }


    // Retrieve data from the database
    fun getScore() : List<HistoryModel> {

        val scoreList = mutableListOf<HistoryModel>()
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT $COLUMN_ID, $COLUMN_SCORE FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC",
            null
        )

        if(cursor.moveToFirst()){
            do {
                val item = HistoryModel(
                    id = cursor.getInt(0),
                    result = cursor.getString(1)
                )

                scoreList.add(item)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return scoreList
    }
}