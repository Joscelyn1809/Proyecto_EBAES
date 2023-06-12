package com.example.proyecto_ebaes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyecto_ebaes.data.room.daos.StudentDao
import com.example.proyecto_ebaes.data.room.models.Student

@Database(
    entities = [Student::class],
    version = 1,
    exportSchema = false
)
abstract class StudentsDB : RoomDatabase() {
    abstract val studentDao: StudentDao
}