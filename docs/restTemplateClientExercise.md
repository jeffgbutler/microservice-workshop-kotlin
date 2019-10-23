# REST Template Client Exercises

The goal of this exercise is to create REST Template based clients for the three basic web services, and then use those client to expose a new composed web service.

All work for this exercise will be in the `movie-aggregator-service-rt` project.

## Application Change
Make the following change to the class `MovieAggregatorServiceRtApplication` in the `microservice.workshop.movieaggregatorservicert` package:

Add a method that will create a REST template like this:

```kotlin
@SpringBootApplication
class MovieAggregatorServiceRtApplication {
	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()
}
```

## Movie Award Client

1. Create a new class called `MovieAwardService` in the `microservice.workshop.movieaggregatorservicert.service` package
1. Annotate the class with `@Service`
1. Add a constructor parameter `private val template: RestTemplate`
1. Add a method `findAwardsForMovie` that takes a movie ID (Int) as a parameter and returns a List of MovieAwards
1. Use a `UriComponentsBuilder` to compose the URL for the target service - something like `http://localhost:8083/award/search?movieId=3`
1. Use the REST template's `exchange` method to retrieve a list of awards

## Movie Cast Client

1. Create a new class called `MovieCastService` in the `microservice.workshop.movieaggregatorservicert.service` package
1. Annotate the class with `@Service`
1. Add a constructor parameter `private val template: RestTemplate`
1. Add a method `findCastMembers` that takes a movie ID (Int) as a parameter and returns a List of CastMembers
1. Use a `UriComponentsBuilder` to compose the URL for the target service - something like `http://localhost:8082/cast/search?movieId=3`
1. Use the REST template's `exchange` method to retrieve a list of cast members

## Movie Client

1. Create a new class called `MovieService` in the `microservice.workshop.movieaggregatorservicert.service` package
1. Annotate the class with `@Service`
1. Add a constructor parameter `private val template: RestTemplate`
1. Add a method `findById` that takes a movie ID (Int) as a parameter and returns a Movie?
1. Use a `UriComponentsBuilder` to compose the URL for the target service - something like `http://localhost:8081/movie/3`
1. Use the REST template's `getForObject` method to retrieve a list of cast members. Use a try/catch construct to catch a possible `HttpClientErrorException` and return `null` if it happens

## Movie Aggregate Service

Make the following change to the class `AggregateMovieService` in the `microservice.workshop.movieaggregatorservicert.service` package

1. Inject the three new services you just created
1. Alter the `findById` method so that it returns an aggregated object. Call the `MovieService` first. If it returns a movie, then call the other two web services to complete the object

Test the application in the following way:

1. Start all three of the individual web services by running the three application classes in each project, or by using the bootRun gradle goal on each project (or better yet, use the SpringBoot support in IntelliJ to start application through the services window)
1. Once all three applications are running, you should be able to run the test `microservice.workshop.movieaggregatorservicert.test.AggregateMovieControllerTest` in the `movie-aggregator-service` project successfully

## Use the Traffic Simulator

Start the movie-aggregator-service-rt application and access it's swagger interface at [http://localhost:8080](http://localhost:8080)

The aggregate service includes a simple SPA (single page application), written with Vue.js, that will send random requests to the aggregate service. This will be very useful when we start to work with Hystrix. You can try it now by navigating to the root of the aggregator project at [http://localhost:8080](http://localhost:8080). Once the page is open in a browser, press the "Start" button to start sending traffic to your new aggregate web service.

Once you are satifsfied that everything is working properly, end all four running web services.
