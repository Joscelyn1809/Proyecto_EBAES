package com.example.proyecto_ebaes.data.firebaseData

data class AlumnoFb(
    val nombreCompleto: String = "",
    val sexo: String = "",
    val correoElectronico: String = "",
    val universidad: String = "",
    val carrera: String = "",
    val semestre: Int? = 0,
    val promedio: Float? = null,
    val fechaNacimiento: Fecha? = null,
    val ciudad: String = "",
    val estado: String = "",
    val estadoCivil: String = "",
    val trastorno: Boolean? = null,
    val tratamiento: Boolean? = null
)

data class Fecha(
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0
)