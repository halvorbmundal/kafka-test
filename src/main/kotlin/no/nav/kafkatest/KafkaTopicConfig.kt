package no.nav.kafkatest

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.kafka.core.KafkaAdmin
import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class KafkaTopicConfig {

    @Value(value = "localhost:9092")

    private val bootstrapAddress: String? = null

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = HashMap<String, Any?>()
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        return KafkaAdmin(configs)
    }

    @Bean
    fun topic1(): NewTopic {
        return NewTopic("testTopic", 1, 1.toShort())
    }
}