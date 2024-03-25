package com.example.newfifthlab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Student::class], version = 1)
abstract class DataBaseClass : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object{
        fun getDataBase(context: Context): DataBaseClass{
            return Room.databaseBuilder(
                context.applicationContext,
                DataBaseClass::class.java,
                "students4.db"
            ).build()
        }
    }
}