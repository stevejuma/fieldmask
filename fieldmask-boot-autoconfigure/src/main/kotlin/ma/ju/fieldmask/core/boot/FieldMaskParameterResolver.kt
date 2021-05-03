package ma.ju.fieldmask.core.boot

import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.core.PathList
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

class FieldMaskParameterResolver(
    private val contextBuilder: FieldMaskContextBuilder,
    private val properties: FieldMaskProperties
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(FieldMaskParameter::class.java) != null
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val request = webRequest.nativeRequest as HttpServletRequest
        val annotation = parameter.getParameterAnnotation(FieldMaskParameter::class.java)!!
        val fields = request.getParameterValues(annotation.name)?.toList()?.joinToString(",") ?: annotation.defaultValue
        val mask: PathList = PathList.matcherFor(fields, properties.separator)
        if (parameter.parameterType.isAssignableFrom(PathList::class.java)) {
            return mask
        } else if (parameter.parameterType.isAssignableFrom(FieldMask.Context::class.java)) {
            return contextBuilder.build(mask, properties)
        }
        throw IllegalArgumentException("invalid return type for FieldParameter. Expected (PathList / FieldMask.Context) got ${parameter.parameterType.simpleName}")
    }
}
