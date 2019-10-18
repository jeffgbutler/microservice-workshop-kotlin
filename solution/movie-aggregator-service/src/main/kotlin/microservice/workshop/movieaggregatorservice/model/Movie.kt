package microservice.workshop.movieaggregatorservice.model

import java.time.LocalDate

class Movie {
    var id: Int = 0
    var title: String? = null
    var releaseDate: LocalDate? = null
    var runLength: Int = 0
    val awards = mutableListOf<MovieAward>()
    val castMembers = mutableListOf<CastMember>()

    fun addAwards(awards: List<MovieAward>) {
        this.awards.addAll(awards)
    }

    fun addCastMembers(castMembers: List<CastMember>) {
        this.castMembers.addAll(castMembers)
    }
}
