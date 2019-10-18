package microservice.workshop.movieawardservice.data

import microservice.workshop.movieawardservice.model.Award
import org.springframework.data.jpa.repository.JpaRepository

interface AwardRepository: JpaRepository<Award, Int> {
    fun findByMovieId(movieId: Int): List<Award>
}
