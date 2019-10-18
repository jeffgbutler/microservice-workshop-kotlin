package microservice.workshop.movieawardservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var movieId: Int? = null
    var movieTitle: String? = null
    var year: Int? = null
    var award: String? = null
}
