package microservice.workshop.movieaggregatorservicert.service

import microservice.workshop.movieaggregatorservicert.model.MovieAward
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class MovieAwardService(private val template: RestTemplate) {

    fun findAwardsForMovie(movieId: Int): List<MovieAward> {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8083")
                .pathSegment("award")
                .pathSegment("search")
                .queryParam("movieId", movieId)
                .toUriString()

        val ent = template.exchange(uri, HttpMethod.GET, null, object : ParameterizedTypeReference<List<MovieAward>>() {})
        return ent.body ?: emptyList()
    }
}
