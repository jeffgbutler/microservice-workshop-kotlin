package microservice.workshop.movieaggregatorservice.http

import microservice.workshop.movieaggregatorservice.service.AggregateMovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie")
class MovieController {

    @Autowired
    lateinit var service: AggregateMovieService

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int) =
            service.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}
