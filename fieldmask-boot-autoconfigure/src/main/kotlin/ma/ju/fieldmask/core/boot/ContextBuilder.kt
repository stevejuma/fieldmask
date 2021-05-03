package ma.ju.fieldmask.core.boot

import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.core.MaskOptions
import ma.ju.fieldmask.core.PathList
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface FieldMaskContextBuilder {
    fun build(paths: PathList, properties: FieldMaskProperties): FieldMask.Context {
        return FieldMask.Context(
            mask = paths,
            options = MaskOptions(
                validateMasks = properties.validate,
                pathOptions = properties.path.copy()
            )
        )
    }

    fun build(fields: String, properties: FieldMaskProperties): FieldMask.Context {
        val mask = if (fields.isBlank()) PathList() else PathList.matcherFor(fields, properties.separator)
        return build(mask, properties)
    }

    fun build(request: HttpServletRequest, response: HttpServletResponse): FieldMask.Context
}

open class DefaultFieldMaskContextBuilder(private val properties: FieldMaskProperties) : FieldMaskContextBuilder {
    override fun build(request: HttpServletRequest, response: HttpServletResponse): FieldMask.Context {
        val fields = request.getParameterValues(properties.fieldsProperty)?.joinToString(",")?.trim() ?: ""
        return build(fields, properties)
    }
}
