package ma.ju.fieldmask.spring

import org.springframework.context.annotation.Import

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldMaskAnnotation

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldMaskResponseBody(
    val enabled: Boolean = true
)

@FieldMaskAnnotation
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(FieldMaskAutoConfiguration::class, FieldMaskControllerAdvice::class, FieldMaskExceptionHandler::class)
annotation class EnableFieldMask

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldMaskParameter(
    val name: String = "fields",
    val defaultValue: String = ""
)
