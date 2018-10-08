package com.fecred.idfor.simplecrm.employee

import com.fecred.idfor.simplecrm.db.Mongo
import com.mongodb.client.FindIterable
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

@Component
class EmployeeService {

    private val collection = "employee"

    @Autowired
    lateinit var mongo: Mongo

    private var count: Long = 0

    fun add(employee: Document) {

        if (!employee.containsKey("name")) {
            throw Exception("Employee must be have a name")
        }

        if (!employee.containsKey("identificationNumber")) {
            throw Exception("Employee must be have a identification number")
        }

        if (!employee.containsKey("address")) {
            throw Exception("Employee must be have a address")
        }

        if (!employee.containsKey("contact")) {
            throw Exception("Employee must be have a contact number")
        }

        mongo.getDb().getCollection(collection).insertOne(employee)

        count ++
    }

    fun count(): Long {
        if (count == 0L) {
            count = mongo.getDb().getCollection(collection).countDocuments()
        }

        return  count
    }
    fun page(page: Int = 0, size: Int = 20): FindIterable<Document> {
        val count = count()
        val maxPage: Int = ceil((count / size).toDouble()).toInt()

        return mongo.getDb().getCollection(collection).find().skip(min(maxPage, page) * size).limit(size)
    }
}