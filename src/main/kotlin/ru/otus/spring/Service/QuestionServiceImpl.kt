package ru.otus.spring.Service

import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import ru.otus.spring.Dto.Question
import java.io.FileReader


@Service
@PropertySource("classpath:application.properties")
class QuestionServiceImpl(
    @Value("\${questions.csv}") private val resource: Resource
) : QuestionService {

    override fun getQuestionsFromCsv() =
        CsvToBeanBuilder<Question>(FileReader(resource.file))
            .withType(Question::class.java)
            .build()
            .parse()

}