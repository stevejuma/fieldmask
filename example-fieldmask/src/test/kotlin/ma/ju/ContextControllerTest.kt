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
class ContextControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    @Test
    fun `filters api response`() {
        mockMvc.perform(get("/context/artists?fields=name,albums(title)"))
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
                    """.trimIndent()
                )
            )
    }

    @Test
    fun `returns custom resolver data`() {
        mockMvc.perform(get("/context/artists?fields=albumCount"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """
                [
                  {
                    "albumCount": 1
                  },
                  {
                    "albumCount": 1
                  }
                ]
                    """.trimIndent()
                )
            )
    }

    @Test
    fun `returns data loader data`() {
        mockMvc.perform(get("/context/artists?fields=name,bestSongs:songs"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """
                [
                  {
                    "name": "Taylor Swift",
                    "bestSongs": [
                      "FearLess",
                      "Fifteen",
                      "Love Story",
                      "Hey Stephen",
                      "White Horse",
                      "You Belong with Me",
                      "Breathe",
                      "Tell Me Why",
                      "You're Not Sorry",
                      "The Way I Loved You",
                      "Forever & Always",
                      "The Best Day",
                      "Change"
                    ]
                  },
                  {
                    "name": "Avril Lavigne",
                    "bestSongs": [
                      "Losing Grip",
                      "Complicated",
                      "Sk8er Boi",
                      "I'm with You",
                      "Mobile",
                      "Unwanted",
                      "Tomorrow",
                      "Anything But Ordinary"
                    ]
                  }
                ]
                    """.trimIndent()
                )
            )
    }
}
