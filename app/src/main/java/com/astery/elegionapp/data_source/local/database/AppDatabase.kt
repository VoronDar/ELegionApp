package com.astery.elegionapp.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.astery.elegionapp.pojo.User

@Database(
    // TODO - add entities
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // TODO - add dao here



    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}