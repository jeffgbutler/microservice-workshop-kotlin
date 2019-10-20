package microservice.workshop.movieaggregatorservice.service.fallback

import microservice.workshop.movieaggregatorservice.model.Movie
import microservice.workshop.movieaggregatorservice.service.MovieService
import org.springframework.stereotype.Service

@Service
class MovieServiceFallback : MovieService {
    override fun findById(id: Int): Movie? = null
}
