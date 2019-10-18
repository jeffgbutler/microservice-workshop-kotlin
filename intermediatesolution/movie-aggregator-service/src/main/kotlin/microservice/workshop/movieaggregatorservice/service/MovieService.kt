package microservice.workshop.movieaggregatorservice.service

import org.springframework.web.bind.annotation.PathVariable
import microservice.workshop.movieaggregatorservice.model.Movie
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "movie-service", decode404 = true, url = "http://localhost:8081")
interface MovieService {

    @GetMapping("/movie/{id}")
    fun findById(@PathVariable("id") id: Int): Movie?
}
