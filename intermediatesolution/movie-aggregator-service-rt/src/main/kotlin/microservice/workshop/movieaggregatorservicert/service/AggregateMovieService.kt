package microservice.workshop.movieaggregatorservicert.service

import org.springframework.stereotype.Service

@Service
class AggregateMovieService(private val movieService: MovieService,
                            private val awardService: MovieAwardService,
                            private val castService: MovieCastService) {

    fun findById(id: Int) =
            movieService.findById(id)?.apply {
                addAwards(awardService.findAwardsForMovie(id))
                addCastMembers(castService.findCastMembers(id))
            }
}
