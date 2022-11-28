package ru.otus.spring.Dto

data class Answer(
    val id: Long,
    val answerText: String,
    var isCorrect: Boolean
)
