package app.restapi.controller

import app.restapi.data.Message
import app.restapi.data.MessageRepository
import app.restapi.request.MessageRequest
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageControllerTests @Autowired constructor(
    private val messageRepository: MessageRepository,
    private val restTemplate: TestRestTemplate
) {

    private val defaultMessageId = ObjectId.get()

    @LocalServerPort
    protected var port: Int = 0

    @BeforeEach
    fun setUp() {
        messageRepository.deleteAll()
    }

    private fun getRootUrl(): String? = "http://localhost:$port/messages"
    private fun saveOneMessage() = messageRepository.save(Message(defaultMessageId, "Name", "Description"))
    private fun prepareMessageRequest() = MessageRequest("Name", "Default description")

    @Test
    fun `should return all messages`() {
        saveOneMessage()

        val response = restTemplate.getForEntity(
            getRootUrl(),
            List::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(1, response.body?.size)
    }

    @Test
    fun `should create new message`() {
        val messageRequest = prepareMessageRequest()

        val response = restTemplate.postForEntity(
            getRootUrl(),
            messageRequest,
            Message::class.java
        )

        assertEquals(201, response.statusCode.value())
        assertNotNull(response.body)
        assertNotNull(response.body?.id)
        assertEquals(messageRequest.description, response.body?.description)
        assertEquals(messageRequest.name, response.body?.name)
    }

}