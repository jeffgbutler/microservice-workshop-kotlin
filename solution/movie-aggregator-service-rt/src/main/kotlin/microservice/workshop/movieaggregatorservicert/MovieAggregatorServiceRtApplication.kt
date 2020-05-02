package microservice.workshop.movieaggregatorservicert

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.time.Duration

@SpringBootApplication
class MovieAggregatorServiceRtApplication {
	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

	@Bean
	fun defaultCbCustomizer(): Customizer<Resilience4JCircuitBreakerFactory>? {
		return Customizer { factory: Resilience4JCircuitBreakerFactory ->
			factory.configureDefault { id: String ->
				Resilience4JConfigBuilder(id)
					.timeLimiterConfig(
						TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build()
					)
					.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
					.build()
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<MovieAggregatorServiceRtApplication>(*args)
}
