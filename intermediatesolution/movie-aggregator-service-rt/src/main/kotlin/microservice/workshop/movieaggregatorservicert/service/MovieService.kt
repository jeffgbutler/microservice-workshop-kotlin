package microservice.workshop.movieaggregatorservicert.service

import microservice.workshop.movieaggregatorservicert.model.Movie
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class MovieService(private val template: RestTemplate) {
    fun findById(id: Int): Movie? {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8081")
                .pathSegment("movie")
                .pathSegment(id.toString())
                .toUriString()

        return try {
            template.getForObject(uri, Movie::class.java)
        } catch (e: HttpClientErrorException) {
            null
        }
    }
}
