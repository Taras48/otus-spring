package ru.otus.spring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.core.io.Resource
import java.util.*

@ConfigurationProperties(prefix = "application")
@ConstructorBinding
data class ApplicationProps(
    val questionsCsv: Resource,
    val locale: Locale
)
