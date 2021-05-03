package ma.ju.fieldmask.core.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * The main entrypoint into the app application
 */
@SpringBootApplication
@EnableFieldMask
@ComponentScan("ma.ju.fieldmask.core.boot.app")
open class Application

/**
 * Bootstraps the application with the given command line [args]
 */
fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
