package ru.otus.spring.Utils

import com.opencsv.bean.AbstractCsvConverter
import ru.otus.spring.Dto.Answer

class TextToAnswer : AbstractCsvConverter() {
    override fun convertToRead(value: String): Answer {
        val splitString = value.split("|")
        return Answer(
            splitString[0].toLong(),
            splitString[1],
            splitString[2].toBoolean()
        )
    }
}