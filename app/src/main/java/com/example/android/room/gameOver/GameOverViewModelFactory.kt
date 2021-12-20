package com.example.android.room.gameOver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.room.gameWon.GameWonViewModel

class GameOverViewModelFactory(private val questionIndex: Int, private val numQuestions: Int, private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameWonViewModel::class.java)) {
            return GameOverViewModel(questionIndex, numQuestions, finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}