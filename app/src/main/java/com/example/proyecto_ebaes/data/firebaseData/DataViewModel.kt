package com.example.proyecto_ebaes.data.firebaseData

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DataViewModel : ViewModel() {
    private val _dataState = MutableStateFlow<List<AlumnoFb>>(emptyList())
    var dataState: StateFlow<List<AlumnoFb>> = _dataState

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _dataState.value = getDataFromFireStore()
//            for (data in dataState) {
//                Log.d("nombre", "${data}")
//            }
        }
    }

    fun getDataByName() {
        viewModelScope.launch {
            val newData = getDataFromFireStore().sortedBy { it.nombreCompleto }
            _dataState.value = newData
        }
    }

    fun getFemaleData() {
        viewModelScope.launch {
            val newData = getDataFromFireStore().filter { it.sexo == "Femenino" }
            _dataState.value = newData
        }
    }

    fun getMaleData() {
        viewModelScope.launch {
            val newData = getDataFromFireStore().filter { it.sexo == "Masculino" }
            _dataState.value = newData
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            val newData = getDataFromFireStore().filter { it.nombreCompleto == name }
            _dataState.value = newData
        }
    }
}

suspend fun getDataFromFireStore(): List<AlumnoFb> {
    val db = FirebaseFirestore.getInstance()
    val users = ArrayList<AlumnoFb>()

    try {
        db.collection("users").get().await().map {
            val result = it.toObject(AlumnoFb::class.java)
            users.add(result)
        }

    } catch (e: FirebaseFirestoreException) {
        Log.d("error", "getDataFromFireStore: $e")
    }
    return users
}