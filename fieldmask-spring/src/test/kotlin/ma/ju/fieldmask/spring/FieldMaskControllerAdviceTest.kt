package ma.ju.fieldmask.spring

import ma.ju.fieldmask.core.BeanMask
import ma.ju.fieldmask.spring.app.Album
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest
class FieldMaskControllerAdviceTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    @Test
    fun `returns entire payload when no fields specified`() {
        val expected = """
        [
          {
            "id": 100,
            "name": "Avril Lavigne"
          },
          {
            "id": 101,
            "name": "Taylor Swift"
          }
        ]
        """
        mockMvc.perform(MockMvcRequestBuilders.get("/artists"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(expected)
            )
        mockMvc.perform(MockMvcRequestBuilders.get("/artists?fields="))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(expected)
            )
    }

    @Test
    fun `returns selected fields only`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/artists?fields=name"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
                [
                  {
                    "name": "Avril Lavigne"
                  },
                  {
                    "name": "Taylor Swift"
                  }
                ]
                """
                )
            )
    }

    @Test
    fun `resolves custom parameters in controller`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/custom-params?paths=singer:name&mask=albums(title)&fields=paths(field,alias)"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """
            [
              {
                "paths": [
                  {
                    "alias": "singer",
                    "field": "singer"
                  }
                ]
              },
              {
                "paths": [
                  {
                    "alias": "",
                    "field": "albums"
                  }
                ]
              },
              {
                "paths": [
                  {
                    "alias": "",
                    "field": "albums"
                  },
                  {
                    "alias": "",
                    "field": "title"
                  }
                ]
              }
            ]
            """
                )
            )
    }

    @Test
    fun `returns correct type`() {
        val entity: Album = Album(2, "Album A1", 1, 2020)
        val mask = BeanMask.mask(entity, "*")
        val result: Album = mask.value()
        Assertions.assertEquals(entity, result)
    }
}
