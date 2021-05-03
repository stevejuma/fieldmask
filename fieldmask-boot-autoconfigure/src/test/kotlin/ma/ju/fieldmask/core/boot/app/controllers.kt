package ma.ju.fieldmask.core.boot.app

import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.core.PathList
import ma.ju.fieldmask.core.boot.FieldMaskParameter
import ma.ju.fieldmask.core.boot.FieldMaskResponseBody
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@FieldMaskResponseBody
class TestController(private val repository: MusicRepository) {
    @RequestMapping("/artists", method = [RequestMethod.GET])
    fun listArtists(): ResponseEntity<List<*>> {
        return ResponseEntity.ok(repository.findAllArtists())
    }

    /**
     * The return type here has to be Any/Object/Generic because we are returning
     * an object that could have partial properties. So unless the return type
     * is a Bean, spring can't correctly serialize the output
     */
    @RequestMapping("/custom-params", method = [RequestMethod.GET])
    @FieldMaskResponseBody(true)
    fun customParameter(
        @FieldMaskParameter("paths")
        paths: PathList,
        @FieldMaskParameter("mask")
        context: FieldMask.Context
    ): List<*> {
        val data = paths.values().toMutableList()
        data.addAll(context.mask.values())
        return data
    }
}
