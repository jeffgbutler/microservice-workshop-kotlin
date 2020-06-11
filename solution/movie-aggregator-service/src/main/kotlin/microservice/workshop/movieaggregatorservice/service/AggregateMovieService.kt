package microservice.workshop.movieaggregatorservice.service

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory
import org.springframework.stereotype.Service

@Service
class AggregateMovieService(
    private val cbFactory: CircuitBreakerFactory<*, *>,
    private val movieService: MovieService,
    private val awardService: MovieAwardService,
    private val castService: MovieCastService
) {

    fun findById(id: Int) =
        findMovie(id)?.apply {
            addAwards(findAwards(id))
            addCastMembers(findCastMembers(id))
        }

    private fun findMovie(id: Int) =
        cbFactory.create("movie-service-cb").run(
            { movieService.findById(id) },
            { null }
        )

    private fun findAwards(id: Int) =
        cbFactory.create("movie-award-service-cb").run(
            { awardService.findAwardsForMovie(id) },
            { emptyList() }
        )

    private fun findCastMembers(id: Int) =
        cbFactory.create("movie-cast-service-cb").run(
            { castService.findCastMembers(id) },
            { emptyList() }
        )
}
