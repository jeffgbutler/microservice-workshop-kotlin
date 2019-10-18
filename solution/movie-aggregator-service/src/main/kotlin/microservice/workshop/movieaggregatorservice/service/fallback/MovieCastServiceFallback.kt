package microservice.workshop.movieaggregatorservice.service.fallback

import microservice.workshop.movieaggregatorservice.model.CastMember
import microservice.workshop.movieaggregatorservice.service.MovieCastService

class MovieCastServiceFallback : MovieCastService {
    override fun findCastMembers(movieId: Int): List<CastMember> = emptyList()
}