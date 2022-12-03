package ru.otus.spring.Service

import ru.otus.spring.Dto.Student

interface StudentService {


    /**
     * Получение студента после теста
     *
     * @return  студент с результатами тестирования
     */
    fun getStudentWithTestResults(): Student

    /**
     * Получение студента
     *
     * @return  студент с заполнеными только именем и фамилией
     */
    fun getStudent(): Student


    /**
     * Выводит студента после теста
     */
    fun printStudentAfterTesting()
}