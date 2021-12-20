package com.example.android.room.gameWon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameWonViewModel(questionIndex: Int, numQuestion: Int, finalScore: Int) : ViewModel() {

    private val _questionCorrect = MutableLiveData<Int>()
    val questionsCorrect: LiveData<Int>
        get() = _questionCorrect

    private val _numQuestions = MutableLiveData<Int>()
    val numQuestions: LiveData<Int>
        get() = _numQuestions

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    init {
        _questionCorrect.value = questionIndex
        _numQuestions.value = numQuestion
        _score.value = finalScore
    }


}