package dev.vini2003.fretando.client.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

inline fun <reified T> RemoteRepository(noinline apiUrl: () -> String) = RemoteRepository(apiUrl, TypeToken.get(T::class.java))

class RemoteRepository<T> (private val apiUrl: () -> String, private val type: TypeToken<T>) {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    private val gson = Gson()

    suspend fun findAll(): List<T> = withContext(Dispatchers.IO) {
        val response = client.get("${apiUrl()}/").bodyAsText()
        gson.fromJson(response, TypeToken.getParameterized(List::class.java, type.type).type)
    }

    suspend fun findById(id: Long): T = withContext(Dispatchers.IO) {
        val response = client.get("${apiUrl()}/$id").bodyAsText()
        gson.fromJson(response, type)
    }

    suspend fun save(entity: T): HttpResponse = withContext(Dispatchers.IO) {
        client.post("${apiUrl()}/") {
            contentType(ContentType.Application.Json)
            setBody(gson.toJson(entity))
        }
    }

    suspend fun update(id: Long, entity: T): HttpResponse = withContext(Dispatchers.IO) {
        client.put("${apiUrl()}/$id") {
            contentType(ContentType.Application.Json)
            setBody(gson.toJson(entity))
        }
    }

    suspend fun deleteById(id: Long): HttpResponse = withContext(Dispatchers.IO) {
        client.delete("${apiUrl()}/$id")
    }
}
