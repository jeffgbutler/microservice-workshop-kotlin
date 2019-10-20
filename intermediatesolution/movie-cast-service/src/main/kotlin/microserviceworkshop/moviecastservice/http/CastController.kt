package microserviceworkshop.moviecastservice.http

import microserviceworkshop.moviecastservice.data.CastMemberRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cast")
class CastController(private val repository: CastMemberRepository) {
    @GetMapping("/search")
    fun search(@RequestParam movieId: Int) =
        ResponseEntity.ok(repository.findByMovieId(movieId))
}
