package microservice.workshop.movieawardservice.http

import microservice.workshop.movieawardservice.data.AwardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/award")
class AwardController {
    @Autowired
    lateinit var repository: AwardRepository

    @GetMapping("/search")
    fun search(@RequestParam("movieId") movieId: Int) =
        ResponseEntity.ok(repository.findByMovieId(movieId))
}
