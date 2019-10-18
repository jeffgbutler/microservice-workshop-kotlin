package microservice.workshop.movieaggregatorservice.service

import org.springframework.web.bind.annotation.RequestParam
import microservice.workshop.movieaggregatorservice.model.MovieAward
import org.springframework.web.bind.annotation.GetMapping
import microservice.workshop.movieaggregatorservice.service.fallback.MovieAwardServiceFallback
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "movie-award-service", fallback = MovieAwardServiceFallback::class)
interface MovieAwardService {

    @GetMapping("/award/search")
    fun findAwardsForMovie(@RequestParam("movieId") movieId: Int): List<MovieAward>
}
