package ru.otus.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.otus.spring.Service.StudentService

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
