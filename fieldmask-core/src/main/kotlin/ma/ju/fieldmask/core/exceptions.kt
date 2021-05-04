package ma.ju.fieldmask.core

/**
 * Exception thrown when there has been an issue with field processing
 * @param message The error that occured
 */
open class FieldException(message: String) : RuntimeException(message)

/**
 * Exception thrown when parsing a string Segment has failed
 */
class ParseException(message: String) : FieldException(message)

/**
 * Exception thrown when the provided field mask does not match the
 * specified Object properties.
 * @property fields The list of offending fields that don't match the object properties
 * @property message Description of the error message
 */
class UnknownFieldMaskException(
    val fields: List<Path>,
    expected: List<Path> = listOf(),
    message: String = "invalid field masks provided: $fields expected one of $expected"
) : FieldException(message)
