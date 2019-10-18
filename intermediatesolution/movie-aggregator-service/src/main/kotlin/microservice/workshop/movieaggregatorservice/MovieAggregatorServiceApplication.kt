package microservice.workshop.movieaggregatorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import microservice.workshop.movieaggregatorservice.service.AggregateMovieService
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients
class MovieAggregatorServiceApplication {
    @Bean
    fun aggregateMovieService() = AggregateMovieService()
}

fun main(args: Array<String>) {
    runApplication<MovieAggregatorServiceApplication>(*args)
}
