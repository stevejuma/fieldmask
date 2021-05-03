package ma.ju.controllers

import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.core.PathList
import ma.ju.fieldmask.core.boot.FieldMaskParameter
import ma.ju.fieldmask.core.boot.FieldMaskResponseBody
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
        paths: PathList,
        @FieldMaskParameter("mask")
        context: FieldMask.Context
    ): Any {
        return mapOf(
            "PathList" to paths.toString(),
            "Context" to context.toString()
        )
    }
}
