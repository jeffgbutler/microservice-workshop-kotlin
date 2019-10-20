package microservice.workshop.movieaggregatorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MovieAggregatorServiceApplication

fun main(args: Array<String>) {
    runApplication<MovieAggregatorServiceApplication>(*args)
}
