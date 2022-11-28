package ru.otus.spring.Dto

import com.opencsv.bean.CsvBindAndSplitByName
import com.opencsv.bean.CsvBindByName
import ru.otus.spring.Utils.TextToAnswer

data class Question(
    @CsvBindByName
    val id: Long = 0,
    @CsvBindByName
    val questionText: String = "",
    @CsvBindAndSplitByName(elementType = Answer::class, splitOn = "\\,", converter = TextToAnswer::class)
    val answers: List<Answer> = listOf()
)
