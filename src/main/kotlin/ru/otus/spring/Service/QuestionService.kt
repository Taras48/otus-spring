package ru.otus.spring.Service

import ru.otus.spring.Dto.Question

interface QuestionService {
    fun getQuestionsFromCsv():List<Question>
}