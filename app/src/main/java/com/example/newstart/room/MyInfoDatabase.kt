package com.example.newstart.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MyInfo::class], version = 1)
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
                    .build()

            return instance!!

        }
//
//        private val roomCallback = object : Callback() {
//             override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                 db.execSQL(CREATE)
//                populateDatabase(instance!!)
//            }
//        }

        private fun populateDatabase(db: MyInfoDatabase) {
            val noteDao = db.noteDao()
            noteDao.insetMyInfo(MyInfo(id = 9,title = "",content = ""))

        }
    }

}