package ma.ju.fieldmask.spring

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import ma.ju.fieldmask.core.BeanMask
import ma.ju.fieldmask.core.ParseException
import ma.ju.fieldmask.core.UnknownFieldMaskException
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

data class FieldMaskException(
    val message: String = "Error while processing the request",
    val status: Int = 400,
    val context: Map<String, Any> = mutableMapOf(),
    @JsonIgnore
    val exception: Exception = Exception(message),
    val type: String = exception.javaClass.simpleName
)

@ControllerAdvice
@ResponseBody
class FieldMaskExceptionHandler : ResponseEntityExceptionHandler() {
    private val logger = LoggerFactory.getLogger(FieldMaskExceptionHandler::class.java)

    @ExceptionHandler(UnknownFieldMaskException::class)
    fun unknownFieldException(
        ex: UnknownFieldMaskException,
    ): ResponseEntity<FieldMaskException> {
        logger.error("unknown FieldMask provided", ex)
        val error = FieldMaskException(
            message = ex.message ?: "",
            status = HttpStatus.BAD_REQUEST.value(),
            context = mapOf("fields" to ex.fields),
            exception = ex
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ParseException::class)
    fun parseException(
        ex: ParseException
    ): ResponseEntity<FieldMaskException> {
        logger.error("invalid FieldMask provided", ex)
        val error = FieldMaskException(
            message = ex.message ?: "",
            status = HttpStatus.BAD_REQUEST.value(),
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}

@ControllerAdvice
class FieldMaskControllerAdvice(
    private val contextBuilder: FieldMaskContextBuilder,
    private val properties: FieldMaskProperties
) : ResponseBodyAdvice<Any?> {
    private val logger = LoggerFactory.getLogger(FieldMaskControllerAdvice::class.java)

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        returnType.getMethodAnnotation(FieldMaskResponseBody::class.java)?.let {
            return it.enabled
        }
        returnType.declaringClass.getAnnotation(FieldMaskResponseBody::class.java)?.let {
            return it.enabled
        }
        return !properties.requireAnnotation
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        logger.debug("Handling body of type: ${body?.javaClass}")
        val httpRequest = (request as ServletServerHttpRequest).servletRequest
        val httpResponse = (response as ServletServerHttpResponse).servletResponse
        body ?: return null
        val context = contextBuilder.build(httpRequest, httpResponse)
        logger.debug("Context: $context")
        if (context.mask.empty()) return body

        val model = BeanMask.mask(body, context).model()
        logger.debug("Body: $model")
        return model
    }
}

val objectMapper = ObjectMapper().apply {
    configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
    findAndRegisterModules()
}

fun <T> BeanMask.Context.parameters(type: Class<T>, mapper: ObjectMapper = objectMapper): T {
    return mapper.convertValue(this.arguments, type)
}

fun <T> BeanMask.Context.parameters(type: TypeReference<T>, mapper: ObjectMapper = objectMapper): T {
    return mapper.convertValue(this.arguments, type)
}

inline fun <reified T> BeanMask.Context.parameters(mapper: ObjectMapper = objectMapper): T {
    return mapper.convertValue(this.arguments, object : TypeReference<T>() {})
}
