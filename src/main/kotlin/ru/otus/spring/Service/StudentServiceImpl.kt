package ru.otus.spring.Service

import org.springframework.stereotype.Service
import ru.otus.spring.Dto.Student
import ru.otus.spring.Dto.TestResult


@Service
class StudentServiceImpl(
    private val questionService: QuestionService,
) : StudentService {
    override fun getStudentWithTestResults(): Student {
        val student = getStudent()

        questionService.getQuestionsFromCsv()
            .map {
                val answers = it.answers
                println(
                    """
                  Выберите вариант ответа на вопрос на вопрос:
                        ${it.questionText}
                  Варианты ответа:                      
                """.trimIndent()
                )
                answers.map { println("${it.id}) ${it.answerText}") }
                val studentAnswer = readLine()?.toLong()
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

    private fun getStudent(): Student {
        println("Введите имя и фамилию: ")
        val nameAndSurname = readLine()?.split(" ")

        return Student(
            name = nameAndSurname?.get(0) ?: "",
            surname = nameAndSurname?.get(1) ?: ""
        )
    }
}