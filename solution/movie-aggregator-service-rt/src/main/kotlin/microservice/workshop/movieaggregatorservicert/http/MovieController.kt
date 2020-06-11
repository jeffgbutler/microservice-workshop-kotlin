package microservice.workshop.movieaggregatorservicert.http

import microservice.workshop.movieaggregatorservicert.service.AggregateMovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movie")
class MovieController(private val service: AggregateMovieService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int) =
        service.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}
