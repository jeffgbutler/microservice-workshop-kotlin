package microservice.workshop.movieaggregatorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.openfeign.EnableFeignClients
import microservice.workshop.movieaggregatorservice.service.fallback.MovieCastServiceFallback
import microservice.workshop.movieaggregatorservice.service.fallback.MovieServiceFallback
import microservice.workshop.movieaggregatorservice.service.fallback.MovieAwardServiceFallback
import microservice.workshop.movieaggregatorservice.service.AggregateMovieService
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
class MovieAggregatorServiceApplication {
    @Bean
    fun aggregateMovieService() = AggregateMovieService()

    @Bean
    fun movieAwardServiceFallback() = MovieAwardServiceFallback()

    @Bean
    fun movieServiceFallback() = MovieServiceFallback()

    @Bean
    fun movieCastServiceFallback() = MovieCastServiceFallback()
}

fun main(args: Array<String>) {
    runApplication<MovieAggregatorServiceApplication>(*args)
}
