package microservice.workshop.movieaggregatorservicert.service

import microservice.workshop.movieaggregatorservicert.model.Movie
import org.springframework.stereotype.Service

@Service
class AggregateMovieService(private val movieService: MovieService,
                            private val awardService: MovieAwardService,
                            private val castService: MovieCastService) {

    fun findById(id: Int) =
            movieService.findById(id)?.augmentMovie()

    private fun Movie.augmentMovie() =
            apply {
                addAwards(awardService.findAwardsForMovie(id))
                addCastMembers(castService.findCastMembers(id))
            }
}
