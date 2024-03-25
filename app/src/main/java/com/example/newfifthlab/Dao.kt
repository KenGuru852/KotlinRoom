package com.example.newfifthlab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(item: List<Student>)
    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>
    @Query("DELETE FROM students")
    fun deleteAllStudents()
}