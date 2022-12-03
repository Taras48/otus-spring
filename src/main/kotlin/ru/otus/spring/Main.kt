package ru.otus.spring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import ru.otus.spring.Service.StudentService

@Configuration
@ComponentScan(basePackages = ["ru.otus.spring"])
class Main

fun main(args: Array<String>) {
    AnnotationConfigApplicationContext(Main::class.java)
        .getBean(StudentService::class.java)
        .printStudentAfterTesting()
}
