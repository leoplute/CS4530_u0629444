package com.example.assignment4

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class FactRepository(private val scope : CoroutineScope,
                     private val dao : FactDao) {

    val allFacts : Flow<List<FactData>> = dao.allFacts()

    fun addFact(fact : FactData){
        scope.launch{
            dao.addFact(fact)
        }
    }

    fun deleteFact(fact : FactData){
        scope.launch {
            dao.deleteFact(fact)
        }
    }
}