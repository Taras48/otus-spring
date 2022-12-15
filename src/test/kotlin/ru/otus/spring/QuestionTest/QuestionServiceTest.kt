package ru.otus.spring.QuestionTest

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import ru.otus.spring.Dto.Answer
import ru.otus.spring.Dto.Question
import ru.otus.spring.Service.QuestionServiceImpl
import ru.otus.spring.config.ApplicationProps
import java.util.*
import kotlin.test.assertEquals


class QuestionServiceTest {
    private val props = ApplicationProps(
        questionsCsv = ClassPathResource("questions.csv"),
        Locale("ru_Ru")
    )

    private val service = QuestionServiceImpl(props)

    @DisplayName("Получение вопросов с ответами из csv")
    @Test
    fun questionsFromCsv() {
        val questions = service.getQuestionsFromCsv()

        val epected = listOf(
            Question(
                1,
                "questionText1",
                listOf(
                    Answer(
                        1,
                        "answer11",
                        true
                    ),
                    Answer(
                        2,
                        "answer12",
                        false
                    )
                )
            ),
            Question(
                2,
                "questionText2",
                listOf(
                    Answer(
                        1,
                        "answer21",
                        true
                    ),
                    Answer(
                        2,
                        "answer22",
                        false
                    )
                )
            ),
            Question(
                3,
                "questionText2",
                listOf(
                    Answer(
                        1,
                        "answer21",
                        true
                    ),
                    Answer(
                        2,
                        "answer22",
                        false
                    )
                )
            ),
            Question(
                4,
                "questionText2",
                listOf(
                    Answer(
                        1,
                        "answer21",
                        true
                    ),
                    Answer(
                        2,
                        "answer22",
                        false
                    )
                )
            ),
            Question(
                5,
                "questionText2",
                listOf(
                    Answer(
                        1,
                        "answer21",
                        false
                    ),
                    Answer(
                        2,
                        "answer22",
                        true
                    )
                )
            )
        )

        assertEquals(epected, questions)
    }
}