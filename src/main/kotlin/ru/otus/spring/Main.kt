package ru.otus.spring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import ru.otus.spring.Service.StudentService

@Configuration
@ComponentScan(basePackages = ["ru.otus.spring"])
class Main

fun main(args: Array<String>) {
    val ctx = AnnotationConfigApplicationContext(Main::class.java)
    val studentService= ctx.getBean(StudentService::class.java)

    studentService.getStudentWithTestResults()
        .let {
            println("Результат теста ${it.name} ${it.surname}: ")
            it.testResults.map { question ->
                println("Вопрос № ${question.questionId} - Ответ: ${question.result}")
            }
            println("Тест ${if (it.check) "сдан!!!" else "не сдан"}")
        }

    ctx.close()
}
