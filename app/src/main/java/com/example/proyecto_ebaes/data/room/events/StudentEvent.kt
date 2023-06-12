package com.example.proyecto_ebaes.data.room.events

import com.example.proyecto_ebaes.data.room.models.Student
import com.example.proyecto_ebaes.data.room.sorts.StudentSortType

sealed interface StudentEvent {

    object SaveStudent : StudentEvent

    data class DeleteStudent(val student: Student): StudentEvent

    data class SetStudentName(val name: String) : StudentEvent
    data class SetStudentEmail(val email: String) : StudentEvent
    data class SetStudentUni(val uni: String) : StudentEvent
    data class SetStudentCareer(val career: String) : StudentEvent
    data class SetStudentCity(val city: String) : StudentEvent
    data class SetStudentState(val estado: String) : StudentEvent
    data class SetStudentCivilState(val civil: String) : StudentEvent
    data class SetStudentBirthdate(val date: String) : StudentEvent
    data class SetStudentGrade(val grade: String) : StudentEvent
    data class SetStudentSemester(val semester: String) : StudentEvent
    data class SetStudentSex(val sex: String) : StudentEvent
    data class SetStudentTranstorno(val transtorno: String) : StudentEvent
    data class SetStudentTreatment(val treatment: String) : StudentEvent

    data class GetStudent(val id: Int): StudentEvent
    data class GetStudentByName(val searchName: String): StudentEvent

    data class SortStudent(val sortType: StudentSortType): StudentEvent
}