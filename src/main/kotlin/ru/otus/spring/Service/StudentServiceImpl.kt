package ru.otus.spring.Service

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.otus.spring.Dto.Student
import ru.otus.spring.Dto.TestResult
import ru.otus.spring.config.ApplicationProps
import ru.otus.spring.exceptions.BadDataInException


@Service
class StudentServiceImpl(
    private val questionService: QuestionService,
    private val forConsoleIOService: IOService,
    private val messageSource: MessageSource,
    private val props: ApplicationProps
) : StudentService {

    override fun getStudentWithTestResults(): Student {
        val student = getStudent()

        questionService.getQuestionsFromCsv()
            .map {
                val answers = it.answers
                forConsoleIOService.printInCons(
                    """
                        ${messageSource.getMessage("answerToQuestion", arrayOf(), props.locale)}                         
                        ${it.questionText}
                        ${messageSource.getMessage("answerOptions", arrayOf(), props.locale)}             
                """.trimIndent()
                )

                answers.map { forConsoleIOService.printInCons("${it.id}) ${it.answerText}") }

                val studentAnswer =
                    forConsoleIOService.readFromCons()?.toLong() ?: throw BadDataInException("Введена пустая строка")

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
        forConsoleIOService.printInCons(messageSource.getMessage("firstAndLastName", arrayOf(), props.locale) )

        val (name, surname) =
            forConsoleIOService.readFromCons()?.let {
                val inStr = it.split(" ")
                if (inStr.size == 2)
                    inStr[0] to inStr[1]
                else
                    throw BadDataInException("Ошибка ввода имени или фамилии.")
            } ?: throw BadDataInException("Введена пустая строка")

        return Student(
            name = name,
            surname = surname
        )
    }

    override fun printStudentAfterTesting() {
        forConsoleIOService.printStudentAfterTestingOnCons(getStudentWithTestResults())
    }
}