package app.restapi.controller

import app.restapi.data.*
import app.restapi.request.MessageRequest
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessageController (
    private val messageRepository: MessageRepository
) {
    @GetMapping
    fun getAllMessages(): ResponseEntity<List<Message>> {
        val messages = messageRepository.findAll()
        return ResponseEntity.ok(messages)
    }

    @PostMapping
    fun createMessage(@RequestBody request: MessageRequest): ResponseEntity<Message> {
        val message = messageRepository.save(Message(
            name = request.name,
            description = request.description
        ))
        return ResponseEntity(message, HttpStatus.CREATED)
    }

}

