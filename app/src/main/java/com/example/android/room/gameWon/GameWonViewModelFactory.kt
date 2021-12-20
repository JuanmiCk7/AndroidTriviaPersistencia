package com.example.android.room.gameWon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameWonViewModelFactory(private val questionIndex: Int, private val numQuestions: Int, private val finalScore: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameWonViewModel::class.java)) {
            return GameWonViewModel(questionIndex, numQuestions, finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}