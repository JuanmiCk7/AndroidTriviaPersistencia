package com.example.android.room.title

import android.util.Log
import androidx.lifecycle.ViewModel

class TitleViewModel : ViewModel() {

    // Nivel seleccionado
    var level = 0
    var nivelAuxiliar = 0


    init {
        Log.i("TitleViewModel", "TitleViewModel creado!")
        level = -1
        nivelAuxiliar = -1
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destruido!")
    }

}