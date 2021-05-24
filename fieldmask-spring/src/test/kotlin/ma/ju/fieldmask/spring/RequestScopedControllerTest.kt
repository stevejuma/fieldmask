package ma.ju.fieldmask.spring

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
class RequestScopedControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    @Test
    fun `filters api response`() {
        mockMvc.perform(get("/scoped/artists?fields=artists(name,albums(title))"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """{
                 "artists": [
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
                    }"""
                )
            )
    }

    @Test
    fun `returns custom resolver data`() {
        mockMvc.perform(get("/scoped/artists?fields=artists/albumCount"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """{
                 "artists": [
                  {
                    "albumCount": 1
                  },
                  {
                    "albumCount": 1
                  }
                ]
                    }"""
                )
            )
    }

    @Test
    fun `returns data loader data`() {
        mockMvc.perform(get("/scoped/artists?fields=artists(name,bestSongs:songs(title))"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """{
               "artists": [
                  {
                    "name": "Avril Lavigne",
                    "bestSongs": [
                      {
                        "title": "Losing Grip"
                      },
                      {
                        "title": "Complicated"
                      },
                      {
                        "title": "Sk8er Boi"
                      },
                      {
                        "title": "I'm with You"
                      }
                    ]
                  },
                  {
                    "name": "Taylor Swift",
                    "bestSongs": [
                      {
                        "title": "FearLess"
                      },
                      {
                        "title": "Fifteen"
                      },
                      {
                        "title": "Love Story"
                      },
                      {
                        "title": "Hey Stephen"
                      }
                    ]
                  }
                ] 
                  } """
                )
            )
    }
}
