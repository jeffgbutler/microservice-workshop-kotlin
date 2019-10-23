package microservice.workshop.movieaggregatorservicert.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import microservice.workshop.movieaggregatorservicert.model.MovieAward
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class MovieAwardService(private val template: RestTemplate, private val discoveryClient: DiscoveryClient) {

    @HystrixCommand(fallbackMethod = "defaultAwards")
    fun findAwardsForMovie(movieId: Int): List<MovieAward> {
        val url = discoveryClient.getInstances("movie-award-service")
                .firstOrNull()?.uri?.toString()
                ?: throw IllegalStateException("movie-award-service not available")

        val uri = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment("award")
                .pathSegment("search")
                .queryParam("movieId", movieId)
                .toUriString()

        val ent = template.exchange(uri, HttpMethod.GET, null, object : ParameterizedTypeReference<List<MovieAward>>() {})
        return ent.body ?: emptyList()
    }

    private fun defaultAwards(movieId: Int) = emptyList<MovieAward>()
}
