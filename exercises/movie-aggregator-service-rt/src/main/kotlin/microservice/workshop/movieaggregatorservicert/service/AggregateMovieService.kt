package microservice.workshop.movieaggregatorservicert.service

import microservice.workshop.movieaggregatorservicert.model.Movie
import org.springframework.stereotype.Service

/*
 * TODO (Exercise 3) - create REST Template clients for the three individual services, then
 * use the clients in this service to compose a complete object. Call the movie-service first.
 * If the movie service returns a value, then call the cast and award services to compose the full
 * movie object.
 */

@Service
class AggregateMovieService {

    fun findById(id: Int) = null as Movie
}
