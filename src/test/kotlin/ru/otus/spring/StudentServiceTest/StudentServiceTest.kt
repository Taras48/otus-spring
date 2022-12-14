package ru.otus.spring.StudentServiceTest

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import ru.otus.spring.Dto.Student
import ru.otus.spring.Dto.TestResult
import ru.otus.spring.Service.*
import ru.otus.spring.config.ApplicationProps
import ru.otus.spring.exceptions.BadDataInException
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals


class StudentServiceTest {
    private val props = ApplicationProps(
        questionsCsv = ClassPathResource("questions.csv"),
        Locale("ru_Ru")
    )
    private val questionService = QuestionServiceImpl(props)
    private val forConsoleIOService = mock<ForConsoleIOServiceImpl>()
    private val messageSource = mock<MessageSource>()
    val service = StudentServiceImpl(questionService, forConsoleIOService, messageSource, props)


    @DisplayName("Получение студента")
    @Test
    fun getStudent() {
        whenever(messageSource.getMessage(any(),any(),any()))
            .thenReturn("")

        whenever(forConsoleIOService.readFromCons())
            .thenReturn("name surname")

        val student = service.getStudent()

        val expected = Student(
            name = "name",
            surname = "surname",
            testResults = mutableListOf(),
            check = false
        )
        assertEquals(expected, student)
    }

    @DisplayName("Получение студента. Ошибка ввода имени или фамилии")
    @Test
    fun getStudentBadNameInFail() {
        whenever(messageSource.getMessage(any(),any(),any()))
            .thenReturn("")
        whenever(forConsoleIOService.readFromCons())
            .thenReturn("name")

        val exception = assertThrows<BadDataInException> {
            service.getStudent()
        }

        assertEquals("Ошибка ввода имени или фамилии.", exception.message)
    }

    @DisplayName("Получение студента. Введена пустая строка")
    @Test
    fun getStudentEmptyInFail() {
        whenever(messageSource.getMessage(any(),any(),any()))
            .thenReturn("")
        whenever(forConsoleIOService.readFromCons())
            .thenReturn(null)

        val exception = assertThrows<BadDataInException> {
            service.getStudent()
        }

        assertEquals("Введена пустая строка", exception.message)
    }

    @DisplayName("Получение студента после теста")
    @Test
    fun getStudentWithTestResultsTest() {
        whenever(messageSource.getMessage(any(),any(),any()))
            .thenReturn("")
        whenever(forConsoleIOService.readFromCons())
            .doReturn("name surname", "1", "1", "1", "1", "1")

        val student = service.getStudentWithTestResults()

        val testResults = mutableListOf(
            TestResult(questionId = 1, result = true),
            TestResult(questionId = 2, result = true),
            TestResult(questionId = 3, result = true),
            TestResult(questionId = 4, result = true),
            TestResult(questionId = 5, result = false)
        )

        val expected = Student(
            name = "name",
            surname = "surname",
            testResults = testResults,
            check = true
        )

        assertEquals(expected, student)
    }

    @DisplayName("Вывод студента после теста")
    @Test
    fun printStudentAfterTestingTest() {
        whenever(messageSource.getMessage(any(),any(),any()))
            .thenReturn("")
        whenever(forConsoleIOService.readFromCons())
            .doReturn("name surname", "1", "1", "1", "1", "1")

        service.printStudentAfterTesting()

        val testResults = mutableListOf(
            TestResult(questionId = 1, result = true),
            TestResult(questionId = 2, result = true),
            TestResult(questionId = 3, result = true),
            TestResult(questionId = 4, result = true),
            TestResult(questionId = 5, result = false)
        )

        val expected = Student(
            name = "name",
            surname = "surname",
            testResults = testResults,
            check = true
        )

        verify(forConsoleIOService, times(1))
            .printStudentAfterTestingOnCons(eq(expected))
    }

}