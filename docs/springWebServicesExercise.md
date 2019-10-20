# Spring Web Services Exercise

The goal of this exercise is to create simple web services using existing JPA repositories. At a minimum, you should create two REST endpoints that can be used for future exercises.

## Movie Cast Service

1. Navigate to the file `microservice.workshop.moviecastservice.http.CastController` in the `movie-cast-service` project
1. Add code to this class so that it will return a list of cast members for a given movie id
    - You should inject the `CastMemberRepository` to gain access to the JPA repository for cast members
    - the REST URL should be `/cast/search?movieId=x` where x is a movie id
1. When you are finished, the tests in the class `microservice.workshop.moviecastservice.http.CastControllerTest` should pass.
1. You can also start the application and access the Swagger ui at [http://localhost:8082](http://localhost:8082)

## Movie Service

1. Navigate to the file `microservice.workshop.movieservice.http.MovieController` in the `movie-service` project
1. Add code to this class so that it will return a movie for a given movie id
    - You should inject the `MovieRepository` to gain access to the JPA repository for cast members
    - the REST URL should be `/movie/x` where x is a movie id
    - If no movie exists, return a 404 (NOT_FOUND) status
1. When you are finished, the tests in the class `microservice.workshop.movieservice.http.MovieControllerTest` should pass.
1. You can also start the application and access the Swagger ui at [http://localhost:8081](http://localhost:8081)
