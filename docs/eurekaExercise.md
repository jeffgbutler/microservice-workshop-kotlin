# Eureka Exercise

In this exercise, we will install and configure a Eureka server for service discovery, then alter all web services to lookup endpoints from Eureka rather than hard coding URLs.

## Eureka Server

There is a simple Spring Boot starter that will stand up a Eureka server with almost no coding required. We'll use the Spring initializer web site to create the server.

1. Navigate to [https://start.spring.io](https://start.spring.io)
1. Create a Maven project with Java and the latest version of Spring Boot (2.1.1 at the time of writing)
1. Specify group: `microservice.workshop`
1. Specify artifact: `eureka-server`
1. For dependencies, add the following:
    - Eureka Server
1. Generate the project (causes a download)
1. Unzip the downloaded file somewhere convenient
1. Add the new project to your IDE workspace
    - Eclipse: File->Import->Existing Maven Project
    - IntelliJ: File->New->Module From Existing Sources...
    - VS Code: File->Add Folder to Workspace
1. Open `microservice.workshop.eurekaserver.EurekaServerApplication.java` and add the `@EnableEurekaServer` annotation to the class
1. Navigate to `src/main/resources`, then rename `application.properties` to `application.yml`
1. Enter the following values in `application.yml`:

    ```yml
    info:
      app:
        name: Eureka Server

    server:
      port: 8761

    eureka:
      client:
        register-with-eureka: false
        fetch-registry: false
    ```

    These changes specify the port for the Eureka server (not really needed in this case because 8761 is default). More importantly, they tell the server not to register with Eureka - without these settings the server would try to register with itself!

1. If you are running with Java9+, then you will need to add a dependency for JAXB. Open `pom.xml` and add the following in the `<dependencies>` section:

    ```xml
    <dependency>
      <groupId>org.glassfish.jaxb</groupId>
      <artifactId>jaxb-runtime</artifactId>
    </dependency>
    ```
1. Once these changes are made, start the server by starting the Spring boot application. After it starts, you can see the Eureka console at [http://localhost:8761](http://localhost:8761)

## Individual Web Services - Register with Eureka

The three individual web services (`movie-servive`, `movie-cast-service`, and `movie-award-service`) need to register themselves with eureka when they start. This is very simple with Spring Boot - we simply have to add the eureka client dependency to the project and Spring Boot will do the magic.

1. Open `pom.xml` in the `movie-award-service` project and uncomment the dependency for `spring-cloud-starter-netflix-eureka-client`
1. Start the `movie-award-service` application
1. Open `pom.xml` in the `movie-cast-service` project and uncomment the dependency for `spring-cloud-starter-netflix-eureka-client`
1. Start the `movie-cast-service` application
1. Open `pom.xml` in the `movie-service` project and uncomment the dependency for `spring-cloud-starter-netflix-eureka-client`
1. Start the `movie-service` application

Once all three `pom.xml` files have been changed and the applications have been started then navigate to the eureka console at [http://localhost:8761](http://localhost:8761) - you should see all three services registered with eureka.

Note that you might see a scary looking warning in the Eureka console. You can safely ignore this warning.

Also note that the Eureka client will look for and register with a Eureka server at `localhost:8761` by default. In production you would need to change this default by configuring a proper production grade Eureka server. See the "Spring Cloud Netflix" documentation for details.

## Configure the Aggregator to Use Eureka

Spring Feign and Eureka have a very simple integration - it's automatic! Our current `@FeignClient` annotations are configured to fund services based on a hard coded URL. If we remove the URL from the annotation, the Feign client will attempt to lookup the service by name from a Eureka server.

1. Open `pom.xml` in the `movie-aggreagator-service` project and uncomment the dependency for `spring-cloud-starter-netflix-eureka-client`
1. Open `MovieAwardService` and remove the `url` attribute from the `@FeignClient` annotation
1. Open `MovieCastService` and remove the `url` attribute from the `@FeignClient` annotation
1. Open `MovieService` and remove the `url` attribute from the `@FeignClient` annotation
1. Start the `movie-aggregator-service` application. If you open the Eureka console, you will see that the aggregator service has also registered itself with eureka.
1. Start the `traffic-simulator` and make sure that information is still being returned correctly from the aggregate service.

Once you are happy with the results, stop the `movie-aggregator-service` - we'll make more changes there for Hystrix in the next exercise.
