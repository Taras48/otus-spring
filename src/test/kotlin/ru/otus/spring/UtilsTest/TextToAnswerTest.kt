package ru.otus.spring.UtilsTest

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.otus.spring.Dto.Answer
import ru.otus.spring.Utils.TextToAnswer
import kotlin.test.assertEquals

class TextToAnswerTest {
    val converter = TextToAnswer()

    @DisplayName("Парсинг строки в ответ")
    @Test
    fun convertToReadTest(){
        val answerString = "1|answer11|true"
        val answer = converter.convertToRead(answerString)
        val expected = Answer(1,"answer11", true)

        assertEquals(expected, answer)
    }
}