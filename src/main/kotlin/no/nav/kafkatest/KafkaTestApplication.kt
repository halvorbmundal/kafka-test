package no.nav.kafkatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaTestApplication

fun main(args: Array<String>) {
	runApplication<KafkaTestApplication>(*args)
}
