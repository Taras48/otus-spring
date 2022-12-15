package ru.otus.spring.Service

import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.stereotype.Service
import ru.otus.spring.Dto.Question
import ru.otus.spring.config.ApplicationProps
import java.io.FileReader


@Service
class QuestionServiceImpl(
    private val props: ApplicationProps
) : QuestionService {

    override fun getQuestionsFromCsv() =
        CsvToBeanBuilder<Question>(FileReader(props.questionsCsv.file))
            .withType(Question::class.java)
            .build()
            .parse()

}