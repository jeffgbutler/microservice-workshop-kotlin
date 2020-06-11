package microservice.workshop.movieaggregatorservicert.service

import microservice.workshop.movieaggregatorservicert.model.CastMember
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class MovieCastService(
    private val template: RestTemplate,
    private val discoveryClient: DiscoveryClient,
    private val cbFactory: CircuitBreakerFactory<*, *>
) {

    fun findCastMembers(movieId: Int): List<CastMember> {
        return cbFactory.create("movie-cast-service-cb").run(
            { getRemoteCastMembers(movieId) },
            { emptyList() }
        )
    }

    private fun getRemoteCastMembers(movieId: Int): List<CastMember> {
        val url = discoveryClient.getInstances("movie-cast-service")
            .firstOrNull()?.uri?.toString()
            ?: throw IllegalStateException("movie-cast-service not available")

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .pathSegment("cast")
            .pathSegment("search")
            .queryParam("movieId", movieId)
            .toUriString()

        val ent =
            template.exchange(uri, HttpMethod.GET, null, object : ParameterizedTypeReference<List<CastMember>>() {})
        return ent.body ?: emptyList()
    }
}
