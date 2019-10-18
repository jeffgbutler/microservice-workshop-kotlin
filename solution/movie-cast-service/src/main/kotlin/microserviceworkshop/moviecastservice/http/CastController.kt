package microserviceworkshop.moviecastservice.http

import microserviceworkshop.moviecastservice.data.CastMemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cast")
class CastController {
    @Autowired
    lateinit var repository: CastMemberRepository

    @GetMapping("/search")
    fun search(@RequestParam movieId: Int) =
        ResponseEntity.ok(repository.findByMovieId(movieId))
}
