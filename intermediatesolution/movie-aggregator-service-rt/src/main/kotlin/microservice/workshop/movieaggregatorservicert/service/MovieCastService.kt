package microservice.workshop.movieaggregatorservicert.service

import microservice.workshop.movieaggregatorservicert.model.CastMember
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class MovieCastService(private val template: RestTemplate) {

    fun findCastMembers(movieId: Int): List<CastMember> {
        val uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8082")
                .pathSegment("cast")
                .pathSegment("search")
                .queryParam("movieId", movieId)
                .toUriString()

        val ent = template.exchange(uri, HttpMethod.GET, null, object : ParameterizedTypeReference<List<CastMember>>() {})
        return ent.body ?: emptyList()
    }
}
