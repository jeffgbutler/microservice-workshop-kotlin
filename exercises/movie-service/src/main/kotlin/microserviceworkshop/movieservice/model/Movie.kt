package microserviceworkshop.movieservice.model

import java.time.LocalDate

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Movie {
    @Id
    var id: Int? = null
    var title: String? = null
    var releaseDate: LocalDate? = null
    var runLength: Int = 0
}
