package microservice.workshop.movieaggregatorservice.service

import org.springframework.web.bind.annotation.RequestParam
import microservice.workshop.movieaggregatorservice.model.CastMember
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "movie-cast-service", url = "http://localhost:8082")
interface MovieCastService {

    @GetMapping("/cast/search")
    fun findCastMembers(@RequestParam("movieId") movieId: Int): List<CastMember>
}