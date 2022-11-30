package ru.otus.spring.Service

import ru.otus.spring.Dto.Student

interface StudentService {

    fun getStudentWithTestResults(): Student
}