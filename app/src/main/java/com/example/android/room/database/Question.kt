package com.example.android.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_of_questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    var questionId: Int,

    @ColumnInfo(name = "question_text")
    var questionText: String,

    @ColumnInfo(name = "question_hint")
    var questionHint: String,

    @ColumnInfo(name = "list_of_answers")
    var questionAnswers: MutableList<String>

)
