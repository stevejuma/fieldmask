package ma.ju.fieldmask.spring

import ma.ju.fieldmask.core.BeanMask
import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.core.MaskOptions
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface FieldMaskContextBuilder {
    fun build(paths: FieldMask, properties: FieldMaskProperties): BeanMask.Context {
        return BeanMask.Context(
            mask = paths,
            options = MaskOptions(
                validateMasks = properties.validate,
                pathOptions = properties.path.copy()
            )
        )
    }

    fun build(fields: String, properties: FieldMaskProperties): BeanMask.Context {
        val mask = if (fields.isBlank()) FieldMask() else FieldMask.matcherFor(fields, properties.separator)
        return build(mask, properties)
    }

    fun build(request: HttpServletRequest, response: HttpServletResponse): BeanMask.Context
}

open class DefaultFieldMaskContextBuilder(private val properties: FieldMaskProperties) : FieldMaskContextBuilder {
    override fun build(request: HttpServletRequest, response: HttpServletResponse): BeanMask.Context {
        val fields = request.getParameterValues(properties.fieldsProperty)?.joinToString(",")?.trim() ?: ""
        return build(fields, properties)
    }
}
