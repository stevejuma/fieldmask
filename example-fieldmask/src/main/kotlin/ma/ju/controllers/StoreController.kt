package ma.ju.controllers

import ma.ju.fieldmask.core.BeanMask
import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.spring.FieldMaskParameter
import ma.ju.fieldmask.spring.FieldMaskResponseBody
import ma.ju.repository.Repository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@FieldMaskResponseBody
class SearchController(private val repository: Repository) {
    @RequestMapping("/artists", method = [RequestMethod.GET])
    fun listArtists(): Any {
        return ResponseEntity.ok(repository.findAllArtists())
    }

    @RequestMapping("/custom-params", method = [RequestMethod.GET])
    @FieldMaskResponseBody(false)
    fun customParameter(
        @FieldMaskParameter("paths")
        paths: FieldMask,
        @FieldMaskParameter("mask")
        context: BeanMask.Context
    ): Any {
        return mapOf(
            "PathList" to paths.toString(),
            "Context" to context.toString()
        )
    }
}
