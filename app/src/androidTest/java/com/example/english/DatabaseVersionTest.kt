package com.example.english

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.english.data.AppDatabase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DatabaseVersionTest {

    private val TEST_DB = "migration-test"

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                    "PRIMARY KEY(`id`))")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Fruit ADD COLUMN price FLOAT")
        }
    }

//    val MIGRATION_1_2 = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE Fruit ADD COLUMN price FLOAT")
//        }
//    }


//    @Rule
//    val helper = MigrationTestHelper(
//        InstrumentationRegistry.getInstrumentation(),
//        SupportSQLiteDatabase::class.java.canonicalName,
//        FrameworkSQLiteOpenHelperFactory()
//    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
//        var db = helper.createDatabase(TEST_DB, 1).apply {
//            execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
//                    "PRIMARY KEY(`id`))")
//            close()
//        }
//        db = helper.runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2)
    }
}