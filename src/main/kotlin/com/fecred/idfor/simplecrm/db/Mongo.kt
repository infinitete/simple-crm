package com.fecred.idfor.simplecrm.db

import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer
import org.springframework.stereotype.Component
import com.mongodb.ServerAddress
import com.mongodb.client.MongoDatabase
import java.util.*


@Component
class Mongo {
    @Value("\${mongo.ip}")
    private lateinit var ip: String

    @Value("\${mongo.port}")
    private lateinit var port: String

    @Value("\${mongo.db}")
    private lateinit var db: String

    private var instance: MongoDatabase? = null

    fun getDb(): MongoDatabase {
        if (instance == null) {
            val mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings { builder -> builder.hosts(Arrays.asList(ServerAddress(ip, port.toInt()))) }
                            .build())

            instance = mongoClient.getDatabase(db)
        }

        return instance!!
    }
}