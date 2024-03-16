package demoserver.com.example.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import kotlinx.html.*

fun Routing.htmlRouting(){
    get("/website"){
        call.respondHtml {
            head {
                title("Ktor Website")
                link(rel = "stylesheet", href = "/style.css", type = "text/css")
            }
            body {
                h1(classes = "title"){ +"Ktor Website" }
                p(classes = "ktor-about-para"){
                    +"Ktor is a framework for building asynchronous servers and clients in connected systems using Kotlin. It is developed by JetBrains, the same company behind the Kotlin programming language. Ktor is designed to be asynchronous from the ground up, which makes it suitable for handling high-concurrency workloads."
                }
                h2(classes = "key-features"){
                    +"Key Features of Ktor"
                }
                ul(classes = "key-points"){
                    li {
                        strong {
                            +"Kotlin-Centric:"
                        }
                        p(classes = "key-features-para"){ +"Ktor is built with Kotlin, which means it leverages Kotlin's features and syntax to provide a concise and expressive way to build web applications. Kotlin's interoperability with Java also allows developers to seamlessly integrate existing Java libraries into Ktor applications." }
                    }
                    li {
                        strong {
                            +"Asynchronous Design:"
                        }

                        p(classes = "key-features-para") { +"Ktor is designed to be asynchronous, meaning it can handle many concurrent connections efficiently. This is achieved through Kotlin's coroutines, which allow developers to write asynchronous code in a sequential, imperative style, making it easier to understand and maintain." }
                    }
                    li {
                        strong {
                            +"Extensible:"
                        }

                        p(classes = "key-features-para"){ +"Ktor follows a modular architecture, allowing developers to choose and include only the components they need for their application. It provides a set of core modules for handling HTTP requests, routing, authentication, and serialization, among others. Additional features can be added through third-party plugins." }
                    }
                    li {
                        strong {
                            +"Embedded Server:"
                        }

                        p(classes = "key-features-para"){ +"Ktor is highly extensible, with support for custom features and plugins. Developers can create their own modules and plugins to extend Ktor's functionality according to their requirements." }
                    }

                }

                div(classes = "btn-container"){
                    a(href = "/website2",){
                        button(classes = "btn-next-page") {
                            +"Next Page"
                        }

                    }
                }
            }

        }
    }

    get("/style.css"){
        call.respondCss {
            body{
                backgroundColor = Color("#131718")
                padding = Padding(LinearDimension("12px"))
            }
            rule("h1.title"){
                color = Color.white
            }
            rule("p.ktor-about-para"){
                color = Color.white
                fontSize = LinearDimension("18px")
            }
            rule("h2.key-features"){
                color = Color.white


            }
            rule("ul.key-points"){
                color = Color.white
                fontSize = LinearDimension("20px")
                margin = Margin(LinearDimension("12px"))
            }
            rule("button.btn-next-page"){
                color = Color.white
                backgroundColor = Color.orange
                border = Border.none
                borderRadius = LinearDimension("5px")
                fontSize = LinearDimension("18px")
                padding = Padding(LinearDimension("12px"))
            }
            rule("div.btn-container"){
                textAlign = TextAlign.center
            }
        }
    }

    get("/website2"){
        call.respondHtml {
            body {
                p{
                    +"This is Website 2"
                }
            }
        }
    }
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}