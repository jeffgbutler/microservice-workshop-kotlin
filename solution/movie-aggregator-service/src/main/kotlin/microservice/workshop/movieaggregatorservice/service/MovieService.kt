package microservice.workshop.movieaggregatorservice.service

import microservice.workshop.movieaggregatorservice.model.Movie
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "movie-service", decode404 = true)
interface MovieService {

    @GetMapping("/movie/{id}")
    fun findById(@PathVariable("id") id: Int): Movie?
}
