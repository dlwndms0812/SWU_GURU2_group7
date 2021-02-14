package com.example.guru_group7.diary

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder

// 마음 다이어리 DataBase Manager
class DiaryDBManager {
    // 데이터베이스명
    var dbName = "MyDiary"
    // 테이블명
    var dbTable = "Diaries"
    // 테이블에 저장될 열 이름
    var colID = "ID"
    var colTitle = "Title"
    var colDes = "Description"
    var colMood = "Mood"
    var colDate = "Date"
    // 데이터 베이스 버전(초기=1)
    var dbVersion = 1

    // MyDiaries DB가 없는 경우 생성
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + colID +
            " INTEGER PRIMARY KEY," + colTitle + " TEXT, " + colDes + " TEXT, " + colMood + " TEXT, "+ colDate + " TEXT);"

    var sqlDB: SQLiteDatabase? = null

    
    constructor(context: Context) {
        var db = DatabaseHelperDiaries(context)
        sqlDB = db.writableDatabase
    }

    inner class DatabaseHelperDiaries : SQLiteOpenHelper {
        var context: Context? = null

        constructor(context: Context) : super(context, dbName, null, dbVersion) {
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS" + dbTable)
        }
    }

    fun insert(values: ContentValues): Long {
        val ID = sqlDB!!.insert(dbTable, "", values)
        return ID
    }

    fun Query(projection: Array<String>, selection: String, selectionArgs: Array<String>, sorOrder: String): Cursor {
        val qb = SQLiteQueryBuilder();
        qb.tables = dbTable
        val cursor = qb.query(sqlDB, projection, selection, selectionArgs, null, null, sorOrder)
        return cursor
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int {
        val count = sqlDB!!.delete(dbTable, selection, selectionArgs)
        return count
    }

    fun update(values: ContentValues, selection: String, selectionArgs: Array<String>): Int {
        val count = sqlDB!!.update(dbTable, values, selection, selectionArgs)
        return count
    }

}