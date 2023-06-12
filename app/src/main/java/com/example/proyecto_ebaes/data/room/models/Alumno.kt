package com.example.proyecto_ebaes.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "STUDENTS_TABLE")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val sexo: String,
    val correo: String,
    val universidad: String,
    val carrera: String,
    val semestre: String,
    val promedio: String,
    val nacimiento: String,
    val ciudad: String,
    val estado: String,
    val estadoCivil: String,
    val transtorno: String,
    val tratamiento:String
)