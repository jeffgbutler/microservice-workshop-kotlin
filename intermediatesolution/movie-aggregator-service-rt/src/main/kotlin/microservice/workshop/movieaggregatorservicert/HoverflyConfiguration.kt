package microservice.workshop.movieaggregatorservicert

import org.apache.http.HttpException
import org.apache.http.HttpHost
import org.apache.http.HttpRequest
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.DefaultProxyRoutePlanner
import org.apache.http.protocol.HttpContext
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.client.RestTemplateCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
@Profile("test")
class HoverflyConfiguration {
    @Bean
    fun restTemplate(): RestTemplate = RestTemplateBuilder(ProxyCustomizer()).build()
}

internal class ProxyCustomizer : RestTemplateCustomizer {
    override fun customize(restTemplate: RestTemplate) {
        val proxy = HttpHost("localhost", 8500)
        val httpClient: HttpClient = HttpClientBuilder.create()
            .useSystemProperties()
            .setRoutePlanner(object : DefaultProxyRoutePlanner(proxy) {
                @Throws(HttpException::class)
                override fun determineProxy(target: HttpHost?, request: HttpRequest?, context: HttpContext?): HttpHost {
                    return super.determineProxy(target, request, context)
                }
            })
            .build()
        restTemplate.requestFactory = HttpComponentsClientHttpRequestFactory(httpClient)
    }
}
