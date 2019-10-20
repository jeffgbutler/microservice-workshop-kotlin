package microserviceworkshop.moviecastservice.http

import microserviceworkshop.moviecastservice.data.CastMemberRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/*
 * TODO (Exercise 3) - Make this a proper rest controller.
 *
 * The controller should respond to a URL like "/cast/search?movieId=x" where x is a movie id.
 * The controller should always return a list of cast members - an empty list is a valid response if
 * there are no cast members for the given ID.
 */

class CastController() {
}
