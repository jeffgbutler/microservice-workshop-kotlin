package microservice.workshop.movieaggregatorservicert.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import microservice.workshop.movieaggregatorservicert.model.CastMember
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.lang.IllegalStateException

@Service
class MovieCastService(private val template: RestTemplate, private val discoveryClient: DiscoveryClient) {

    @HystrixCommand(fallbackMethod = "defaultCastMembers")
    fun findCastMembers(movieId: Int): List<CastMember> {
        val url = discoveryClient.getInstances("movie-cast-service")
                .stream()
                .findAny()
                .map { it.uri.toString() }
                .orElseThrow{ IllegalStateException("movie-cast-service not available") }

        val uri = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment("cast")
                .pathSegment("search")
                .queryParam("movieId", movieId)
                .toUriString()

        val ent = template.exchange(uri, HttpMethod.GET, null, object : ParameterizedTypeReference<List<CastMember>>() {})
        return ent.body ?: emptyList()
    }

    private fun defaultCastMembers(movieId: Int) = emptyList<CastMember>()
}
