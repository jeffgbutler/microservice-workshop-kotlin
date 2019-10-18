package microserviceworkshop.moviecastservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieCastServiceApplication

fun main(args: Array<String>) {
	runApplication<MovieCastServiceApplication>(*args)
}
