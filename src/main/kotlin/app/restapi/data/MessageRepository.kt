package app.restapi.data

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MessageRepository : MongoRepository<Message, String> {
    override fun deleteAll()
}