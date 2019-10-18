package microserviceworkshop.movieservice.http

import microserviceworkshop.movieservice.data.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movie")
class MovieController {
    @Autowired
    lateinit var repository: MovieRepository

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int) =
        ResponseEntity.of(repository.findById(id))
}
