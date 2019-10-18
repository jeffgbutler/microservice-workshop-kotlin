package microservice.workshop.movieaggregatorservice.service

import microservice.workshop.movieaggregatorservice.model.Movie
import org.springframework.beans.factory.annotation.Autowired

class AggregateMovieService {

    @Autowired
    lateinit var movieService: MovieService
    @Autowired
    lateinit var awardService: MovieAwardService
    @Autowired
    lateinit var castService: MovieCastService

    fun findById(id: Int) =
            movieService.findById(id)?.augmentMovie()

    private fun Movie.augmentMovie() =
            apply {
                addAwards(awardService.findAwardsForMovie(id))
                addCastMembers(castService.findCastMembers(id))
            }
}
