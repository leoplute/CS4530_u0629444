package com.example.assignment4

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json



@Serializable
data class FunFact(var text:String, var source_url:String?=null)


class FactRepository(
    private val scope : CoroutineScope,
    private val dao : FactDao,
    private val client : HttpClient
    ) {

    val allFacts : Flow<List<FactData>> = dao.allFacts()

    suspend fun fetchSaveFact(){
        val response : FunFact = client.get("https://uselessfacts.jsph.pl/random.json?language=en").body()
        val entity = FactData(id=0, text=response.text)
        dao.addFact(entity)
    }

    fun deleteFact(fact : FactData) {
        scope.launch {
            dao.deleteFact(fact)
        }
    }
}