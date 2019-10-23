# Feign Client Exercises

The goal of this exercise is to create Open Feign based clients for the three basic web services, and then use those client to expose a new composed web service.

All work for this exercise will be in the `movie-aggregator-service` project.

## Movie Award Client

1. Create a new interface called `MovieAwardService` in the `microservice.workshop.movieaggregatorservice.service` package
1. Enable this interface to be a Feign client for the existing movie award web service with the following characteristics:
    - use the name `movie-award-service` for the Feign client.
    - use the URL `http://localhost:8083`
    - write a method that will return a `List<MovieAward>` for a given movie id

## Movie Cast Client

1. Create a new interface called `MovieCastService` in the `microservice.workshop.movieaggregatorservice.service` package
1. Enable this interface to be a Feign client for the existing movie cast web service with the following characteristics:
    - use the name `movie-cast-service` for the Feign client.
    - use the URL `http://localhost:8082`
    - write a method that will return a `List<CastMember>` for a given movie id

## Movie Client

1. Create a new interface called `MovieService` in the `microservice.workshop.movieaggregatorservice.service` package
1. Enable this interface to be a Feign client for the existing movie web service with the following characteristics:
    - use the name `movie-service` for the Feign client.
    - use the URL `http://localhost:8081`
    - write a method that will return `Movie?` for a given movie id

## Movie Aggregate Service

Make the following change to the class `AggregateMovieService` in the `microservice.workshop.movieaggregatorservice.service` package

1. Inject the three new services you just created
1. Alter the `findById` method so that it returns an aggregated object. Call the `MovieService` first. If it returns a movie, then call the other two web services to complete the object

## Enable Feign

In the class `microservice.workshop.movieaggregatorservice.MovieAggregatorServiceApplication`, add the `@EnableFeignClients` annotation.

Test the application in the following way:

1. Start all three of the individual web services by running the three application classes in each project, or by using the bootRun gradle goal on each project (or better yet, use the SpringBoot support in IntelliJ to start application through the services window)
1. Once all three applications are running, you should be able to run the test `microservice.workshop.movieaggregatorservice.test.AggregateMovieControllerTest` in the `movie-aggregator-service` project successfully

## Use the Traffic Simulator

Start the movie-aggregator-service application and access it's swagger interface at [http://localhost:8080](http://localhost:8080)

The aggregate service includes a simple SPA (single page application), written with Vue.js, that will send random requests to the aggregate service. This will be very useful when we start to work with Hystrix. You can try it now by navigating to the root of the aggregator project at [http://localhost:8080](http://localhost:8080). Once the page is open in a browser, press the "Start" button to start sending traffic to your new aggregate web service.

Once you are satifsfied that everything is working properly, end all four running web services.
