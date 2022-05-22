package com.example.newstart.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MyInfo::class], version = 2)
abstract class MyInfoDatabase:RoomDatabase() {
    abstract fun noteDao(): MyInfoDao

    companion object {
        private var instance: MyInfoDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): MyInfoDatabase {
            if(instance == null)
                instance = databaseBuilder(ctx.applicationContext, MyInfoDatabase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                   // .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build()

            return instance!!

        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE date_time ADD COLUMN description TEXT NOT NULL DEFAULT 1")
            }
        }
//
//        private val roomCallback = object : Callback() {
//             override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                 db.execSQL(CREATE)
//                populateDatabase(instance!!)
//            }
//        }


    }

}