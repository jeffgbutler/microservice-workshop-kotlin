package microserviceworkshop.movieservice.http

import org.hamcrest.Matchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@SpringBootTest
class MovieControllerTests {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private val webApplicationContext: WebApplicationContext? = null

    @BeforeEach
    fun setup() {
        mockMvc = webAppContextSetup(webApplicationContext!!).build()
    }

    @Test
    @Throws(Exception::class)
    fun testExists() {
        mockMvc.perform(get("/movie/1"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", `is`(1)))
                .andExpect(jsonPath("$.title", `is`("The Godfather")))
    }

    @Test
    @Throws(Exception::class)
    fun testNotExists() {
        mockMvc.perform(get("/movie/21"))
                .andExpect(status().`is`(HttpStatus.NOT_FOUND.value()))
    }
}
