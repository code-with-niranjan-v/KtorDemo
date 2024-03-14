package demoserver.com.example.routes

import demoserver.com.example.models.Customer
import demoserver.com.example.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.request.*

fun Route.CustomerRouting(){
    route("/"){
        get {
            call.respondText("Welcome to My API Service!")
        }
    }
    route("/customer"){
        get {
            if (customerStorage.isEmpty()){
                call.respondText("No Customers!", status = HttpStatusCode.OK)
            }else
            {
                call.respond(customerStorage)
            }
        }

        get("{id?}"){
            val id = call.parameters["id"]?: return@get call.respondText("Id Not Found", status = HttpStatusCode.BadRequest)
            val customer = customerStorage.find { it.id == id } ?: call.respondText("No customer with id $id", status = HttpStatusCode.NotFound)

            call.respond(customer)
        }

        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Created Successfully", status = HttpStatusCode.OK)
        }

        delete("{id?}"){
            val id = call.parameters["id"] ?: call.respondText("Mission Id", status = HttpStatusCode.BadRequest)
            val customer = customerStorage.removeIf { it.id == id }
            call.respondText("Customer with id $id was deleted", status = HttpStatusCode.Created)
        }
    }
}