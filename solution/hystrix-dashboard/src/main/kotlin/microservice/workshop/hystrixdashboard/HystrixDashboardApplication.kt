package microservice.workshop.hystrixdashboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView

@SpringBootApplication
@EnableHystrixDashboard
@Controller
class HystrixDashboardApplication {
	@RequestMapping("/")
	fun redirectToHystrix() = RedirectView("hystrix")
}

fun main(args: Array<String>) {
	runApplication<HystrixDashboardApplication>(*args)
}
