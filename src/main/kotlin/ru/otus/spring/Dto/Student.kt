package ru.otus.spring.Dto

data class Student(
    val id: String = "",
    val name: String,
    val surname: String,
    val testResults: MutableList<TestResult> = mutableListOf(),
    var check: Boolean = false
)
