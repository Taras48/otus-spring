package ru.otus.spring.shell

import org.springframework.context.MessageSource
import org.springframework.shell.Availability
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability
import ru.otus.spring.Service.IOService
import ru.otus.spring.Service.StudentService
import ru.otus.spring.config.ApplicationProps
import ru.otus.spring.exceptions.BadDataInException
import kotlin.random.Random


@ShellComponent
class AppCommands(
    private val props: ApplicationProps,
    private val messageSource: MessageSource,
    private val forConsoleIOService: IOService,
    private val studentService: StudentService,
) {
    private var captcha = false


    //    @ShellMethodAvailability(value = ["isPublishEventCommandAvailable"])
    @ShellMethod(value = "Login", key = ["l", "login"])
    fun login(): String {
        val randomNumb = List(2) { Random.nextInt(0, 100) }
        println( messageSource.getMessage("captcha", randomNumb.toTypedArray(), props.locale))
        val sum =
            forConsoleIOService.readFromCons()?.toInt() ?: throw BadDataInException("Ошибка ввода теста captcha.")

        if (sum == randomNumb.sum()) {
            captcha = true
            return messageSource.getMessage("startTesting", randomNumb.toTypedArray(), props.locale)
        } else{
            return messageSource.getMessage("badCaptcha", arrayOf(), props.locale)
        }
    }

    @ShellMethod(value = "Start testing", key = ["t", "test"])
    fun test() = studentService.printStudentAfterTesting()

    @ShellMethodAvailability("test")
    fun availabilityCheck(): Availability {
        return if (captcha) Availability.available() else Availability.unavailable("you are not connected")
    }
}