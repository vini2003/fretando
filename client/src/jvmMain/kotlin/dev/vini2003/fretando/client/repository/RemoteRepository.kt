package dev.vini2003.fretando.client.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class RemoteRepository<T>(val apiUrl: String) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend inline fun <reified T> findAll(): List<T> = withContext(Dispatchers.IO) {
        client.get(apiUrl).body()
    }

    suspend inline fun <reified T> findById(id: String): T = withContext(Dispatchers.IO) {
        client.get("$apiUrl/$id").body()
    }

    suspend inline fun <reified T> save(entity: T): HttpResponse = withContext(Dispatchers.IO) {
        client.post(apiUrl) {
            contentType(ContentType.Application.Json)
            setBody(entity)
        }
    }

    suspend inline fun <reified T> update(id: String, entity: T): HttpResponse = withContext(Dispatchers.IO) {
        client.put("$apiUrl/$id") {
            contentType(ContentType.Application.Json)
            setBody(entity)
        }
    }

    suspend inline fun <reified T> deleteById(id: String): HttpResponse = withContext(Dispatchers.IO) {
        client.delete("$apiUrl/$id")
    }
}
