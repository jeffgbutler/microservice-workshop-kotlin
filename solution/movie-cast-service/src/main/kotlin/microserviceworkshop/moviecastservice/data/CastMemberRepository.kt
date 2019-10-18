package microserviceworkshop.moviecastservice.data

import microserviceworkshop.moviecastservice.model.CastMember
import org.springframework.data.jpa.repository.JpaRepository

interface CastMemberRepository: JpaRepository<CastMember, Int> {
    fun findByMovieId(movieId: Int): List<CastMember>
}
