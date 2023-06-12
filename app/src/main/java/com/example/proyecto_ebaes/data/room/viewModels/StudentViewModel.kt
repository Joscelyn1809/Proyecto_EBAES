package com.example.proyecto_ebaes.data.room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_ebaes.data.room.StudentState
import com.example.proyecto_ebaes.data.room.daos.StudentDao
import com.example.proyecto_ebaes.data.room.events.StudentEvent
import com.example.proyecto_ebaes.data.room.models.Student
import com.example.proyecto_ebaes.data.room.sorts.StudentSortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentViewModel(
    private val studentDao: StudentDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(StudentSortType.ALL)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _students = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                StudentSortType.ALL -> studentDao.getAllStudents()
                StudentSortType.STUDENT_NAME -> studentDao.getAllStudentsOrderedByName()
                StudentSortType.FEMALE -> studentDao.getSexStudentsOrderedByName("Femenino")
                StudentSortType.MALE -> studentDao.getSexStudentsOrderedByName("Masculino")
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(StudentState())
    val state = combine(_state, _sortType, _students) { state, sortType, students ->
        state.copy(
            alumnos = students,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StudentState())

    fun onEvent(event: StudentEvent) {
        when (event) {

            StudentEvent.SaveStudent -> {
                val name = state.value.nombre
                val correo = state.value.correo
                val uni = state.value.universidad
                val carrera = state.value.carrera
                val ciudad = state.value.ciudad
                val estado = state.value.estado
                val estadoCivil = state.value.estadoCivil
                val nacimiento = state.value.nacimiento
                val promedio = state.value.promedio
                val semestre = state.value.semestre
                val sexo = state.value.sexo
                val transtorno = state.value.transtorno
                val tratamiento = state.value.tratamiento

                if (name.isBlank() || correo.isBlank() || uni.isBlank() || carrera.isBlank()
                    || ciudad.isBlank() || estado.isBlank() || estadoCivil.isBlank()
                    || nacimiento.isBlank() || promedio.isBlank() || semestre.isBlank()
                    || sexo.isBlank() || transtorno.isBlank() || tratamiento.isBlank()
                ) {
                    return
                }

                val student = Student(
                    nombre = name,
                    correo = correo,
                    universidad = uni,
                    carrera = carrera,
                    semestre = semestre,
                    promedio = promedio,
                    nacimiento = nacimiento,
                    ciudad = ciudad,
                    estadoCivil = estadoCivil,
                    estado = estado,
                    sexo = sexo,
                    transtorno = transtorno,
                    tratamiento = tratamiento
                )

                viewModelScope.launch {
                    studentDao.upsertStudent(student)
                    _state.update {
                        it.copy(
                            nombre = name,
                            correo = correo,
                            universidad = uni,
                            carrera = carrera,
                            semestre = semestre,
                            promedio = promedio,
                            nacimiento = nacimiento,
                            ciudad = ciudad,
                            estadoCivil = estadoCivil,
                            estado = estado,
                            sexo = sexo,
                            transtorno = transtorno,
                            tratamiento = tratamiento
                        )
                    }
                }

            }

            is StudentEvent.DeleteStudent -> {
                viewModelScope.launch {
                    studentDao.deleteStudent(event.student)
                }
            }

            is StudentEvent.SetStudentName -> {
                _state.update {
                    it.copy(
                        nombre = event.name
                    )
                }
            }

            is StudentEvent.SetStudentEmail -> {
                _state.update {
                    it.copy(
                        correo = event.email
                    )
                }
            }

            is StudentEvent.SetStudentUni -> {
                _state.update {
                    it.copy(
                        universidad = event.uni
                    )
                }
            }

            is StudentEvent.SetStudentCareer -> {
                _state.update {
                    it.copy(
                        carrera = event.career
                    )
                }
            }

            is StudentEvent.SetStudentCity -> {
                _state.update {
                    it.copy(
                        ciudad = event.city
                    )
                }
            }

            is StudentEvent.SetStudentState -> {
                _state.update {
                    it.copy(
                        estado = event.estado
                    )
                }
            }

            is StudentEvent.SetStudentCivilState -> {
                _state.update {
                    it.copy(
                        estadoCivil = event.civil
                    )
                }
            }

            is StudentEvent.SetStudentBirthdate -> {
                _state.update {
                    it.copy(
                        nacimiento = event.date
                    )
                }
            }

            is StudentEvent.SetStudentGrade -> {
                _state.update {
                    it.copy(
                        promedio = event.grade
                    )
                }
            }

            is StudentEvent.SetStudentSemester -> {
                _state.update {
                    it.copy(
                        semestre = event.semester
                    )
                }
            }

            is StudentEvent.SetStudentSex -> {
                _state.update {
                    it.copy(
                        sexo = event.sex
                    )
                }
            }

            is StudentEvent.SetStudentTranstorno -> {
                _state.update {
                    it.copy(
                        transtorno = event.transtorno
                    )
                }
            }

            is StudentEvent.SetStudentTreatment -> {
                _state.update {
                    it.copy(
                        tratamiento = event.treatment
                    )
                }
            }

            is StudentEvent.GetStudent -> {
                viewModelScope.launch {
                    studentDao.getStudentById(event.id)
                }
            }

            is StudentEvent.GetStudentByName -> {
                viewModelScope.launch {
                    studentDao.getStudentByName(event.searchName)
                }
            }

            is StudentEvent.SortStudent -> {
                _sortType.value = event.sortType
            }
        }
    }
}