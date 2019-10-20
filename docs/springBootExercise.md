# Spring Boot Exercise

## Create the Basic Application

1. Navigate to [https://start.spring.io](https://start.spring.io)
1. Create a Gradle project with Kotlin and the latest version of Spring Boot (2.2.0 at the time of writing)
1. Specify group: `microservice.workshop`
1. Specify artifact: `springboot-demo`
1. For dependencies, add the following:
    - Spring Web
    - Spring Boot Actuator
    - Spring Data JPA
    - H2 Database
1. Generate the project (causes a download)
1. Unzip the downloaded file somewhere convenient
1. Add the new project to your IDE workspace (we only recommend IntelliJ for Kotlin Projects)
    - IntelliJ: Import Project, then point to the unzipped directory
1. Rename `application.properties` in `src/main/resources` to `application.yml`

## Configure The Info Actuator

1. Open `application.yml` in `src/main/resources`
1. Add this value

    ```yml
    info:
      app:
        name: Person Service

    management:
      endpoint:
        health:
          show-details: always
    ```


## Configure Swagger

1. Open `build.gradle.kts`, add the following dependencies:

    ```kotlin
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
    ```

1. Create a class `SwaggerConfiguration` in the `micoservice.workshop.springbootdemo` package. Add the following:

    ```kotlin
    package microservice.workshop.springbootdemo

    import org.springframework.context.annotation.Bean
    import org.springframework.context.annotation.Configuration
    import org.springframework.stereotype.Controller
    import springfox.documentation.swagger2.annotations.EnableSwagger2
    import org.springframework.web.bind.annotation.RestController
    import springfox.documentation.builders.RequestHandlerSelectors
    import springfox.documentation.spi.DocumentationType
    import springfox.documentation.spring.web.plugins.Docket
    import org.springframework.web.servlet.view.RedirectView
    import org.springframework.web.bind.annotation.RequestMapping

    @Configuration
    @EnableSwagger2
    @Controller
    class SwaggerConfiguration {
        @RequestMapping("/")
        fun redirectToSwagger()= RedirectView("swagger-ui.html")

        @Bean
        fun api(): Docket {
            return Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
                    .build()
        }
    }
    ```

    This configuration does three important things:

    1. It enables Swagger
    1. It redirects the root URL to the Swagger UI. I find this convenient, but YMMV
    1. It tells Springfox that we only want to use Swagger for REST controllers. Without this there will be Swagger documentation for the redirect controller, as well as the basic Spring error controller and we usually don't want this.

## Create a Person Repository

1. Create a package `microservice.workshop.springbootdemo.model`
1. Create a class in the new package called `Person`
1. Set the content of `Person` to the following:

    ```kotlin
    package microservice.workshop.springbootdemo.model

    import javax.persistence.Entity
    import javax.persistence.GenerationType
    import javax.persistence.GeneratedValue
    import javax.persistence.Id

    @Entity
    data class Person(
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            var id: Int?,
            var firstName: String?,
            var lastName: String?
    )
    ```

1. Create a package `microservice.workshop.springbootdemo.data`
1. Create an interface in the new package called `PersonRepository`
1. Set the content of `PersonRepository` to the following:

    ```kotlin
    package microservice.workshop.springbootdemo.data

    import microservice.workshop.springbootdemo.model.Person
    import org.springframework.data.jpa.repository.JpaRepository

    interface PersonRepository: JpaRepository<Person, Int> {
        fun findByLastName(lastName: String): List<Person>
    }
    ```
1. Create a file called `import.sql` in `src/main/resources`. Set the contents to the following:

    ```sql
    insert into person(first_name, last_name) values('Fred', 'Flintstone');
    insert into person(first_name, last_name) values('Wilma', 'Flintstone');
    insert into person(first_name, last_name) values('Barney', 'Rubble');
    insert into person(first_name, last_name) values('Betty', 'Rubble');
    ```

## Create a REST Controller

1. Create a package `microservice.workshop.springbootdemo.http`
1. Create a class in the new package called `PersonController`
1. Set the content of `PersonController` to the following:

    ```kotlin
    package microservice.workshop.springbootdemo.http

    import microservice.workshop.springbootdemo.data.PersonRepository
    import org.springframework.http.ResponseEntity
    import org.springframework.web.bind.annotation.*

    @RestController
    @RequestMapping("/person")
    class PersonController(private val repository: PersonRepository) {

        @GetMapping
        fun findAll() =
                ResponseEntity.ok(repository.findAll())

        @GetMapping("/{id}")
        fun findById(@PathVariable("id") id: Int) =
                ResponseEntity.of(repository.findById(id))

        @GetMapping("/search")
        fun search(@RequestParam("lastName") lastName: String) =
                ResponseEntity.ok(repository.findByLastName(lastName))
    }
    ```

## Unit Tests

1. Make a new package `microservice.workshop.springbootdemo.http` in the `src/test/kotlin` tree
1. Create a class in the new package called `PersonControllerTest`
1. Set the content of `PersonControllerTest` to the following:

    ```kotlin
    package microservice.workshop.springbootdemo.http

    import org.hamcrest.Matchers.`is`
    import org.hamcrest.Matchers.hasSize
    import org.junit.jupiter.api.BeforeEach
    import org.junit.jupiter.api.Test
    import org.junit.jupiter.api.extension.ExtendWith
    import org.springframework.boot.test.context.SpringBootTest
    import org.springframework.http.HttpStatus
    import org.springframework.http.MediaType
    import org.springframework.test.context.junit.jupiter.SpringExtension
    import org.springframework.test.web.servlet.MockMvc
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
    import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
    import org.springframework.test.web.servlet.setup.MockMvcBuilders
    import org.springframework.web.context.WebApplicationContext

    @ExtendWith(SpringExtension::class)
    @SpringBootTest
    class PersonControllerTest(private val webApplicationContext: WebApplicationContext) {
        private lateinit var mockMvc: MockMvc

        @BeforeEach
        fun setup() {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        }

        @Test
        @Throws(Exception::class)
        fun testFindAll() {
            mockMvc.perform(get("/person"))
                    .andExpect(status().`is`(HttpStatus.OK.value()))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize<Any>(4)))
        }

        @Test
        @Throws(Exception::class)
        fun testFindOne() {
            mockMvc.perform(get("/person/1"))
                    .andExpect(status().`is`(HttpStatus.OK.value()))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName", `is`("Fred")))
                    .andExpect(jsonPath("$.lastName", `is`("Flintstone")))
        }

        @Test
        @Throws(Exception::class)
        fun testFindNone() {
            mockMvc.perform(get("/person/22"))
                    .andExpect(status().`is`(HttpStatus.NOT_FOUND.value()))
        }

        @Test
        @Throws(Exception::class)
        fun testSearch() {
            mockMvc.perform(get("/person/search?lastName=Rubble"))
                    .andExpect(status().`is`(HttpStatus.OK.value()))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize<Any>(2)))
        }
    }
    ```

## Testing

1. Run the unit tests:
    - (Windows Command Prompt) `gradlew clean test`
    - (Windows Powershell) `.\gradlew clean test`
    - (Mac/Linux) `./gradlew clean test`
    - Or your IDE's method of running tests

1. Start the application:
    - (Windows Command Prompt) `gradlew bootRun`
    - (Windows Powershell) `.\gradlew  bootRun`
    - (Mac/Linux) `./gradlew bootRun`
    - Or your IDE's method of running the main application class

1. Test Swagger [http://localhost:8080](http://localhost:8080)
1. Test the acuator health endpoint [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
1. Test the acuator info endpoint [http://localhost:8080/actuator/info](http://localhost:8080/actuator/info)
