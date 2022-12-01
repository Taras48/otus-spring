package ru.otus.spring.Utils

import org.springframework.stereotype.Component

@Component
class IOUtils() {
    fun readFromCons() = readLine()

    fun printInCons(string: String) = println(string)
}