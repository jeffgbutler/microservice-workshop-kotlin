package microservice.workshop.movieawardservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MovieAwardServiceApplication

fun main(args: Array<String>) {
	runApplication<MovieAwardServiceApplication>(*args)
}
