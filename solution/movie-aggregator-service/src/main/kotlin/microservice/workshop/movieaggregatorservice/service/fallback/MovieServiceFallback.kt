package microservice.workshop.movieaggregatorservice.service.fallback

import microservice.workshop.movieaggregatorservice.model.Movie
import microservice.workshop.movieaggregatorservice.service.MovieService

class MovieServiceFallback : MovieService {
    override fun findById(id: Int): Movie? = null
}
