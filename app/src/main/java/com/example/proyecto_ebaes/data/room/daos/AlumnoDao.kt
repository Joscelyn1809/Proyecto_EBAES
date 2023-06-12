package com.example.proyecto_ebaes.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.proyecto_ebaes.data.room.models.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Upsert
    suspend fun upsertStudent(alumno: Student)

    @Delete
    suspend fun deleteStudent(Student: Student)

    @Query("SELECT * FROM STUDENTS_TABLE")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM StudentS_TABLE ORDER BY nombre ASC")
    fun getAllStudentsOrderedByName(): Flow<List<Student>>

    @Query("SELECT * FROM STUDENTS_TABLE WHERE sexo = :sexo")
    fun getSexStudentsOrderedByName(sexo: String): Flow<List<Student>>

    @Query("SELECT * FROM StudentS_TABLE WHERE id = :id")
    fun getStudentById(id: Int): Flow<List<Student>>

    @Query("SELECT * FROM StudentS_TABLE WHERE nombre = :nombre")
    fun getStudentByName(nombre: String): Flow<List<Student>>
}