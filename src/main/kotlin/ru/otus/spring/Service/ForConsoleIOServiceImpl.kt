package ru.otus.spring.Service

import org.springframework.stereotype.Service
import ru.otus.spring.Dto.Student

@Service
class forConsoleIOServicempl : IOService {
    override fun readFromCons() = readLine()

    override fun printInCons(string: String) = println(string)


    override fun printStudentAfterTestingOnCons(student: Student) {
        printInCons("Результат теста ${student.name} ${student.surname}: ")

        student.testResults.map { question ->
            printInCons("Вопрос № ${question.questionId} - Ответ: ${question.result}")
        }
        printInCons("Тест ${if (student.check) "сдан!!!" else "не сдан"}")
    }
}