package microservice.workshop.movieaggregatorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
/*
 * TODO (Exercise 4) - uncomment this annotation to enable Feign clients
 *
 * @EnableFeignClients
 */
/*
 * TODO (Exercise 6) - uncomment this annotation to turn on Hystrix
 *
 * @EnableCircuitBreaker
 */
class MovieAggregatorServiceApplication

fun main(args: Array<String>) {
    runApplication<MovieAggregatorServiceApplication>(*args)
}
