package ru.otus.spring.Config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.Resource
import ru.otus.spring.Service.QuestionServiceImpl
import ru.otus.spring.Service.StudentServiceImpl

//@Configuration
//@PropertySource("classpath:application.properties")
//class SpringConfig(
//    @Value("\${questions.csv}") private val resource: Resource
//) {
//    @Bean
//    fun getQuestionService() = QuestionServiceImpl(resource)
//
//    @Bean
//    fun getStudentService() = StudentServiceImpl(getQuestionService())
//}