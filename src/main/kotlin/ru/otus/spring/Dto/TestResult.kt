package ru.otus.spring.Dto

data class TestResult(
    val id: String = "",
    val questionId: Long = 0,
    val result: Boolean = false,
)
