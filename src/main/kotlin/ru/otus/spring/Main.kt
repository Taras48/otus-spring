import org.springframework.context.support.ClassPathXmlApplicationContext
import ru.otus.spring.Service.QuestionService

fun main(args: Array<String>) {

    val ctx = ClassPathXmlApplicationContext("spring-context.xml")
    val questionService = ctx.getBean(QuestionService::class.java)

    questionService.getQuestionsFromCsv()
        .map {
            println(
                """
                Вопрос № ${it.id}:
                    ${it.questionText}
                Варианты ответов:
                    ${it.answers.joinToString { it.answerText}}
            """.trimIndent()
            )
        }
}