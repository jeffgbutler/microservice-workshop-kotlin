package microservice.workshop.movieaggregatorservicert.http

import io.specto.hoverfly.junit.core.Hoverfly
import io.specto.hoverfly.junit.core.SimulationSource.dsl
import io.specto.hoverfly.junit.dsl.HoverflyDsl.service
import io.specto.hoverfly.junit.dsl.HttpBodyConverter.jsonWithSingleQuotes
import io.specto.hoverfly.junit.dsl.ResponseCreators.notFound
import io.specto.hoverfly.junit.dsl.ResponseCreators.success
import io.specto.hoverfly.junit5.HoverflyExtension
import io.specto.hoverfly.junit5.api.HoverflyConfig
import io.specto.hoverfly.junit5.api.HoverflyCore
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class, HoverflyExtension::class)
@HoverflyCore(config = HoverflyConfig(adminPort = 8888, proxyPort = 8500))
@SpringBootTest
@ActiveProfiles("test")
class AggregateMovieControllerTest {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @BeforeEach
    fun setup(h: Hoverfly) {
        this.mockMvc = webAppContextSetup(webApplicationContext).build()
        h.simulate(dsl(
            service("https://movie-service.apps.pas.jgbpcf.net").apply {
                get("/movie/1").willReturn(success().body(jsonWithSingleQuotes(
                    "{'id': 1, 'title': 'The Godfather', 'releaseDate': '1972-03-24', 'runLength': 175}")))
                get("/movie/21").willReturn(notFound())
            },
            service("https://movie-award-service.apps.pas.jgbpcf.net").apply {
                get("/award/search").queryParam("movieId", "1").willReturn(success().body(jsonWithSingleQuotes("""
                [
                    {'id': 1,'movieId': 1,'movieTitle': 'The Godfather','year': 1973,'award': 'Best Picture'},
                    {'id': 2,'movieId': 1,'movieTitle': 'The Godfather','year': 1973,'award': 'Best Actor'},
                    {'id': 3,'movieId': 1,'movieTitle': 'The Godfather','year': 1973,'award': 'Best Writing'}
                ]
                """.trimIndent())))
            },
            service("https://movie-cast-service.apps.pas.jgbpcf.net").apply {
                get("/cast/search").queryParam("movieId", "1").willReturn(success().body(jsonWithSingleQuotes("""
                [
                    {'id': 1,'movieId': 1,'movieTitle': 'The Godfather','role': 'Michael Corleone','actor': 'Al Pacino'},
                    {'id': 2,'movieId': 1,'movieTitle': 'The Godfather','role': 'Don Vito Corleone','actor': 'Marlon Brando'},
                    {'id': 3,'movieId': 1,'movieTitle': 'The Godfather','role': 'Sonny Corleone','actor': 'James Caan'},
                    {'id': 4,'movieId': 1,'movieTitle': 'The Godfather','role': 'Kay Adams','actor': 'Diane Keaton'},
                    {'id': 5,'movieId': 1,'movieTitle': 'The Godfather','role': 'Connie','actor': 'Talia Shire'},
                    {'id': 6,'movieId': 1,'movieTitle': 'The Godfather','role': 'Tom Hagen','actor': 'Robert Duvall'}
                ]
                """.trimIndent())))
            }
        ))
    }

    @Test
    @Throws(Exception::class)
    fun testExists() {
        mockMvc.perform(get("/movie/1"))
            .andExpect(status().`is`(HttpStatus.OK.value()))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title", `is`("The Godfather")))
            .andExpect(jsonPath("$.awards", hasSize<Any>(3)))
            .andExpect(jsonPath("$.castMembers", hasSize<Any>(6)))
    }

    @Test
    @Throws(Exception::class)
    fun testNotExists() {
        mockMvc.perform(get("/movie/21"))
            .andExpect(status().`is`(HttpStatus.NOT_FOUND.value()))
    }
}
