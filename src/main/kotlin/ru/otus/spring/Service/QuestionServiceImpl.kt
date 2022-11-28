package ru.otus.spring.Service

import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.core.io.Resource
import ru.otus.spring.Dto.Question
import java.io.FileReader

class QuestionServiceImpl(
    private val resource: Resource
) : QuestionService {

    override fun getQuestionsFromCsv() =
        CsvToBeanBuilder<Question>(FileReader(resource.file))
            .withType(Question::class.java)
            .build()
            .parse()
}