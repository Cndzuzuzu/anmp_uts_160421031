package com.zuzudev.hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zuzudev.hobbyapp.util.DB_NAME
import com.zuzudev.hobbyapp.util.MIGRATION_1_2

@Database(entities = arrayOf(Berita::class, Users::class, Page::class), version =  1)
abstract class HobbyDatabase: RoomDatabase()  {
    abstract fun hobbyDao(): HobbyDao
    companion object {
        @Volatile private var instance: HobbyDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,
            HobbyDatabase::class.java, DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .build()
        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }


}