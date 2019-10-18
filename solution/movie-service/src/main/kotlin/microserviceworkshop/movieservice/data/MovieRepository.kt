package microserviceworkshop.movieservice.data

import microserviceworkshop.movieservice.model.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<Movie, Int>
