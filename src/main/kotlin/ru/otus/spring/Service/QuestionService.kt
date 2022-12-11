package ru.otus.spring.Service

import ru.otus.spring.Dto.Question

interface QuestionService {
    /**
     * Получение вопросов с возможными варантами ответов из ресурсов
     *
     * @return  список вопросов
     */
    fun getQuestionsFromCsv():List<Question>
}