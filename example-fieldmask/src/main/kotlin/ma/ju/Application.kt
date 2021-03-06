package ma.ju

import ma.ju.fieldmask.spring.EnableFieldMask
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * The main entrypoint into the app application
 */
@SpringBootApplication
@EnableFieldMask
@ComponentScan("ma.ju.repository", "ma.ju.controllers")
open class Application

/**
 * Bootstraps the application with the given command line [args]
 */
fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
