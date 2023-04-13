package BankApplicationTask.BankApplicationTask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
//@EnableJpaRepositories
class BankApplicationTaskApplication

fun main(args: Array<String>) {
	runApplication<BankApplicationTaskApplication>(*args)
}
