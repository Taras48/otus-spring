package ru.otus.spring.Service

import ru.otus.spring.Dto.Student

interface IOService {
    /**
     * Читает с консоли
     *
     * @return  строка с консоли
     */
    fun readFromCons(): String?

    /**
     * Выводит на консоль
     */
    fun printInCons(string: String)

    /**
     * Выводит студента на консоль после теста
     */
    fun printStudentAfterTestingOnCons(student: Student)
}