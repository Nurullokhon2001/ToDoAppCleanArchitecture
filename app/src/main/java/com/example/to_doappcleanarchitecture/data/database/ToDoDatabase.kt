package com.example.to_doappcleanarchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.to_doappcleanarchitecture.data.converter.Converter
import com.example.to_doappcleanarchitecture.data.dao.ToDoDao
import com.example.to_doappcleanarchitecture.domain.model.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

//    companion object {
//
//        @Volatile
//        private var INSTANCE: ToDoDatabase? = null
//
//        fun getDatabase(context: Context): ToDoDatabase {
//            Log.e("getDatabase", "getDatabase: ")
//
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ToDoDatabase::class.java,
//                    "todo_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}