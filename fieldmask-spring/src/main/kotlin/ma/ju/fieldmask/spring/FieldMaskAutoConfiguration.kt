package ma.ju.fieldmask.spring

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import ma.ju.fieldmask.core.FieldModel
import ma.ju.fieldmask.core.ParseException
import ma.ju.fieldmask.core.UnknownFieldMaskException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableConfigurationProperties(FieldMaskProperties::class)
open class FieldMaskAutoConfiguration : WebMvcConfigurer {
    @Autowired
    private lateinit var fieldMaskProperties: FieldMaskProperties

    @Bean
    @ConditionalOnMissingBean(type = ["FieldMaskContextBuilder"])
    open fun fieldMaskContextBuilder() = DefaultFieldMaskContextBuilder(fieldMaskProperties)

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(FieldMaskParameterResolver(fieldMaskContextBuilder(), fieldMaskProperties))
    }

    @ExceptionHandler(UnknownFieldMaskException::class, ParseException::class)
    fun handleInvalidMask(ex: UnknownFieldMaskException, res: HttpServletResponse) {
        res.sendError(HttpStatus.BAD_REQUEST.value(), ex.message)
    }
}

inline fun <reified T> FieldModel<*>.value(mapper: ObjectMapper = ObjectMapper()): T {
    return mapper.convertValue(model(), object : TypeReference<T>() {})
}
