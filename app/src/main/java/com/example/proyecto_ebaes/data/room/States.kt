package com.example.proyecto_ebaes.data.room

import com.example.proyecto_ebaes.data.room.models.Student
import com.example.proyecto_ebaes.data.room.sorts.StudentSortType

data class StudentState(
    val alumnos: List<Student> = emptyList(),
    val nombre: String = "",
    val sexo: String = "",
    val correo: String = "",
    val universidad: String = "",
    val carrera: String = "",
    val semestre: String = "",
    val promedio: String = "",
    val nacimiento: String = "",
    val ciudad: String = "",
    val estado: String = "",
    val estadoCivil: String = "",
    val transtorno: String = "",
    val tratamiento: String = "",
    val sortType: StudentSortType = StudentSortType.ALL
)