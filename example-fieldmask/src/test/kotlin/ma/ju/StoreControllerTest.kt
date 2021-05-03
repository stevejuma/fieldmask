package ma.ju

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest
class StoreControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    @Test
    fun `filters api response`() {
        mockMvc.perform(get("/artists?fields=name,albums(title)"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """
                [
                  {
                    "albums": [
                      {
                        "title": "FearLess"
                      }
                    ],
                    "name": "Taylor Swift"
                  },
                  {
                    "albums": [
                      {
                        "title": "Let Go"
                      }
                    ],
                    "name": "Avril Lavigne"
                  }
                ]
            """
                )
            )
    }

    @Test
    fun `filters api response with aliases`() {
        mockMvc.perform(get("/artists?fields=singer:name,albums(title)"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """
               [
                  {
                    "albums": [
                      {
                        "title": "FearLess"
                      }
                    ],
                    "singer": "Taylor Swift"
                  },
                  {
                    "albums": [
                      {
                        "title": "Let Go"
                      }
                    ],
                    "singer": "Avril Lavigne"
                  }
               ] 
            """
                )
            )
    }
}
