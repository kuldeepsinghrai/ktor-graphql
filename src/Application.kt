package com.kuldeepsinghrai

import com.apurebase.kgraphql.GraphQL
import com.kuldeepsinghrai.graphql.dessertSchema
import io.ktor.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(GraphQL) {
        playground = true
        schema {
            dessertSchema()
        }
    }
}