package com.example.newstart.room

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Ashutosh Ojha on 23,May,2022
 */
@RunWith(AndroidJUnit4::class)
class MyInfoDatabaseTest {
    private lateinit var database: SupportSQLiteDatabase

    @JvmField
    @Rule
    val testHelper =
        MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            MyInfoDatabase::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory()
        )

    @Test
    fun migrate1to2() {

        //Here we have created  our old version  1 db  and inseted data to it using SQL command because
        // we can use room way of queries only on latest version  of db
        database = testHelper.createDatabase("test_db", 1).apply {
            execSQL("INSERT INTO  date_time VALUES (5, \"10-12-2022\", \"hello\")".trimIndent())
            close()
        }

        //Created our migration db
        database = testHelper.runMigrationsAndValidate("test_db", 2, true, MIGRATION_1_2)

        //Created our db using Room
        val studentDb = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            MyInfoDatabase::class.java,
            "test_db"
        ).allowMainThreadQueries().build()

        val info= studentDb.noteDao().getMyInfo().first()

        //Checking if data got preserved in version 2
        assertEquals("hello", info.content)

        //Checking if we have got expected default  value for description
        assertEquals("1", info.description)

    }

      val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE date_time ADD COLUMN description TEXT NOT NULL DEFAULT 1")
        }
    }

}