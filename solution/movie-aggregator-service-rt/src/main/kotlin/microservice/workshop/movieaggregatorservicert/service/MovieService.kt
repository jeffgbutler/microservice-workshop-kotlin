package microservice.workshop.movieaggregatorservicert.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import microservice.workshop.movieaggregatorservicert.model.Movie
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class MovieService(private val template: RestTemplate, private val discoveryClient: DiscoveryClient) {

    @HystrixCommand(fallbackMethod = "unknownMovie")
    fun findById(id: Int): Movie? {
        val url = discoveryClient.getInstances("movie-service")
                .firstOrNull()?.uri?.toString()
                ?: throw IllegalStateException("movie-service not available")

        val uri = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment("movie")
                .pathSegment(id.toString())
                .toUriString()

        return try {
            template.getForObject(uri, Movie::class.java)
        } catch (e: HttpClientErrorException) {
            null
        }
    }

    private fun unknownMovie(id: Int): Movie? = null
}
