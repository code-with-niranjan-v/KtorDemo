package demoserver.com.example

import demoserver.com.example.plugins.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureSerialization()

}

fun Application.module2(){
    routing {
        post("/upload") {
            call.receiveMultipart().forEachPart { part ->
                when (part) {
                    is PartData.FileItem -> {
                        val fileBytes = part.streamProvider().readBytes()
                        val fileName = part.originalFileName ?: "unknown_file"
                        val destinationFile = File("$fileName")

                        destinationFile.writeBytes(fileBytes)

                        call.respondText("File uploaded successfully: $fileName")
                    }
                    else -> {
                        // Handle other part types if needed
                        part.dispose()
                    }
                }
            }
        }
    }
}


