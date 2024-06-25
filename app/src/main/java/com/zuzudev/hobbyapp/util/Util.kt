package com.zuzudev.hobbyapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zuzudev.hobbyapp.model.HobbyDatabase

val DB_NAME = "hobbyapp"

fun buildDb(context: Context):HobbyDatabase {
    val db = Room.databaseBuilder(context,
        HobbyDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}


val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "")
    }
}