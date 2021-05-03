package ma.ju.fieldmask.core.boot

import ma.ju.fieldmask.core.PathOptions
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("fieldmask")
open class FieldMaskProperties {
    /**
     * Whether we need to check for the `@FieldMaskResponseBody` annotation on a method or class
     * before we apply the field masks
     */
    var requireAnnotation: Boolean = true

    /**
     * Whether partial responses are enabled
     */
    var enabled: Boolean = true

    /**
     * Whether to validate specified fields in the request
     */
    var validate: Boolean = true

    /**
     * The bean path fetch optionss
     */
    var path: PathOptions = PathOptions()

    /**
     *  The property in the http request to search for the selection fields
     */
    var fieldsProperty = "fields"

    /**
     * The separator to use for different depths of a given path
     */
    var separator = "/"

    fun copy(
        requireAnnotation: Boolean? = null,
        validate: Boolean? = null,
        path: PathOptions? = null,
        fieldsProperty: String? = null,
        separator: String? = null
    ): FieldMaskProperties {
        val opts = FieldMaskProperties()
        opts.requireAnnotation = requireAnnotation ?: opts.requireAnnotation
        opts.validate = validate ?: opts.validate
        opts.path = path ?: opts.path
        opts.fieldsProperty = fieldsProperty ?: opts.fieldsProperty
        opts.separator = separator ?: opts.separator
        return opts
    }
}
