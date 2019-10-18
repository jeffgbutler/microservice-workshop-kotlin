package microservice.workshop.movieaggregatorservice.service

import org.springframework.web.bind.annotation.RequestParam
import microservice.workshop.movieaggregatorservice.model.MovieAward
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "movie-award-service", url = "http://localhost:8083")
interface MovieAwardService {

    @GetMapping("/award/search")
    fun findAwardsForMovie(@RequestParam("movieId") movieId: Int): List<MovieAward>
}
