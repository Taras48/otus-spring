package ru.otus.spring.StudentServiceTest

import org.junit.jupiter.api.DisplayName
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import ru.otus.spring.Dto.Student
import ru.otus.spring.Dto.TestResult
import ru.otus.spring.Service.QuestionServiceImpl
import ru.otus.spring.Service.StudentServiceImpl
import ru.otus.spring.Utils.IOUtils
import kotlin.test.Test
import kotlin.test.assertEquals


class StudentServiceTest {
    private val resource: Resource = ClassPathResource("questions.csv")
    private val questionService = QuestionServiceImpl(resource)
    private val ioUtils = mock<IOUtils>()
    val service = StudentServiceImpl(questionService, ioUtils)


    @DisplayName("Получение студента")
    @Test
    fun getStudent() {
        whenever(ioUtils.readFromCons())
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

    @DisplayName("Получение студента после теста")
    @Test
    fun getStudentWithTestResultsTest() {
        whenever(ioUtils.readFromCons())
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

}