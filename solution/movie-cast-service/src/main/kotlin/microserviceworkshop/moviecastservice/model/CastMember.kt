package microserviceworkshop.moviecastservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class CastMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var movieId: Int? = null
    var movieTitle: String? = null
    var role: String? = null
    var actor: String? = null
}
