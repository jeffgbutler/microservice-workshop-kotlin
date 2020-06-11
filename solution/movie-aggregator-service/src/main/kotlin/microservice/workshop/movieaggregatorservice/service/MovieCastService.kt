package microservice.workshop.movieaggregatorservice.service

import microservice.workshop.movieaggregatorservice.model.CastMember
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "movie-cast-service")
interface MovieCastService {

    @GetMapping("/cast/search")
    fun findCastMembers(@RequestParam("movieId") movieId: Int): List<CastMember>
}