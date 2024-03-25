package com.example.newfifthlab

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "students")
data class Student(
    @PrimaryKey()
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "weight")
    var weight: Int,
    @ColumnInfo(name = "height")
    var height: Int,
    @ColumnInfo(name = "age")
    var age: Int
)