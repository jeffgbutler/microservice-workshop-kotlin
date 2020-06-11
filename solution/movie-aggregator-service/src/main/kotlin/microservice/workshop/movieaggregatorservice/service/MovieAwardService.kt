package microservice.workshop.movieaggregatorservice.service

import microservice.workshop.movieaggregatorservice.model.MovieAward
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "movie-award-service")
interface MovieAwardService {

    @GetMapping("/award/search")
    fun findAwardsForMovie(@RequestParam("movieId") movieId: Int): List<MovieAward>
}
