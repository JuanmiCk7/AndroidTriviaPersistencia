package com.example.android.room.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    data class Question(
        val text: String,
        val answers: List<String>,
        val hint: String
    )

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    lateinit var answers: MutableList<String>

    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    var numQuestions = 0

    private val _hint = MutableLiveData<String>()
    val hint: LiveData<String>
        get() = _hint

    private val _hintUsed = MutableLiveData<Boolean>()
    val hintUsed: LiveData<Boolean>
        get() = _hintUsed

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _streak  = MutableLiveData<Int>()
    val streak: LiveData<Int>
        get() = _streak


    init {
        Log.i("GameViewModel", "GameViewModel creado!")
        _questionIndex.value = 0
        _hint.value = ""
        _hintUsed.value = false
        _score.value = 0
        _streak.value = 1
        _currentQuestion.value = null

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destruido!")
    }

    fun setQuestion(currentQuestion: Question) {
        if (_currentQuestion.value == null) {
            _currentQuestion.value = currentQuestion
            Log.i("ViewModel", this.currentQuestion.value?.text.toString())
            Log.i("ViewModel", this.currentQuestion.value?.answers?.get(0).toString())
            Log.i("ViewModel", this.currentQuestion.value?.answers?.get(1).toString())
            Log.i("ViewModel", this.currentQuestion.value?.answers?.get(2).toString())
            Log.i("ViewModel", this.currentQuestion.value?.answers?.get(3).toString())
            Log.i("ViewModel", this.currentQuestion.value?.hint.toString())
        }
    }

    fun setCurrentHint() {
        _hint.value =  currentQuestion.value?.hint.toString()
    }

    fun resetHintUsed() {
        _hintUsed.value = false
    }

    fun setHintOnTrue() {
        _hintUsed.value = true
    }



    fun plusScoreHintUsed() {
        var scoreAux = _score.value
        var streakAux = _streak.value
        _score.value = scoreAux!! + ((streakAux!! * 10) / 2)
        Log.i("ViewModel", "Hint Used!")
    }

    fun plusScoreNoHintUsed() {
        var scoreAux = _score.value
        var streakAux = _streak.value
        _score.value = scoreAux!! + (streakAux!! * 10)
        Log.i("ViewModel", "Hint Not Used!")
    }

    fun plusQuestionIndex() {
        _questionIndex.value = _questionIndex.value?.plus(1)
        _currentQuestion.value = null
    }

    fun plusStreak() {
        _streak.value = _streak.value?.plus(1)
    }




}