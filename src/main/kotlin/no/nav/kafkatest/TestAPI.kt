package no.nav.kafkatest

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.web.bind.annotation.*
import java.io.IOException




@RestController
public class TestAPI {
    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>;

    private var lastMessage: String="";

    fun sendMessage(msg: String) {
        kafkaTemplate.send("testTopic", msg)
    }


    @Autowired
    private lateinit var kafkaListenerContainerFactory: ConcurrentKafkaListenerContainerFactory<String, String>;

    @KafkaListener(topics = ["testTopic"], groupId = "testGroup")
    @Throws(IOException::class)
    fun consume(message: String) {
        println(message)
        lastMessage = message
    }

    @PutMapping("/{text}")
    fun sendToKafka(@PathVariable text: String) : String{
        sendMessage(text) // returns a future object
        return text
    }

    @GetMapping
    fun getAllFromTestTopic():String{
        println(lastMessage)
        return lastMessage
    }

}
