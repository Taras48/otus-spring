package ru.otus.spring.Service

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.otus.spring.Dto.Student
import ru.otus.spring.config.ApplicationProps

@Service
class ForConsoleIOServiceImpl(
    private val props: ApplicationProps,
    private val messageSource: MessageSource
) : IOService {
    override fun readFromCons() = readLine()

    override fun printInCons(string: String) = println(string)


    override fun printStudentAfterTestingOnCons(student: Student) {
        printInCons("${messageSource.getMessage("result", arrayOf(student.name, student.surname), props.locale)} : ")

        student.testResults.map { question ->
            printInCons(
                """${messageSource.getMessage("question", arrayOf(question.questionId), props.locale)} -
                ${messageSource.getMessage("answer", arrayOf(question.result), props.locale)}""".trimMargin()
            )
        }
        printInCons(
            """${messageSource.getMessage("test", arrayOf(), props.locale)} 
            ${
                if (student.check)
                    messageSource.getMessage("check", arrayOf(), props.locale)
                else
                    messageSource.getMessage("unCheck", kotlin.arrayOf(), props.locale)
            }""".trimMargin()
        )
    }
}