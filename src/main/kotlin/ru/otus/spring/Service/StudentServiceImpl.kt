package ru.otus.spring.Service

import org.springframework.stereotype.Service
import ru.otus.spring.Dto.Student
import ru.otus.spring.Dto.TestResult
import ru.otus.spring.Utils.IOUtils


@Service
class StudentServiceImpl(
    private val questionService: QuestionService,
    private val ioUtils: IOUtils,
) : StudentService {
    override fun getStudentWithTestResults(): Student {
        val student = getStudent()

        questionService.getQuestionsFromCsv()
            .map {
                val answers = it.answers
                ioUtils.printInCons(
                    """
                  Выберите вариант ответа на вопрос на вопрос:
                        ${it.questionText}
                  Варианты ответа:                      
                """.trimIndent()
                )

                answers.map { ioUtils.printInCons("${it.id}) ${it.answerText}") }

                val studentAnswer = ioUtils.readFromCons()?.toLong()
                val result = answers.find { it.id == studentAnswer }?.isCorrect ?: false

                student.testResults.add(
                    TestResult(
                        questionId = it.id,
                        result = result
                    )
                )
            }
        student.check = student.testResults.filter { it.result }.size >= 3

        return student
    }

    override fun getStudent(): Student {
        ioUtils.printInCons("Введите имя и фамилию: ")
        val (name, surname) = ioUtils.readFromCons()!!.split(" ")

        return Student(
            name = name,
            surname = surname
        )
    }
}