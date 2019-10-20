package microservice.workshop.movieaggregatorservice.service.fallback

import microservice.workshop.movieaggregatorservice.model.MovieAward
import microservice.workshop.movieaggregatorservice.service.MovieAwardService
import org.springframework.stereotype.Service

@Service
class MovieAwardServiceFallback : MovieAwardService {
    override fun findAwardsForMovie(movieId: Int): List<MovieAward> = emptyList()
}
