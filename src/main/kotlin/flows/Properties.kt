package flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    runBlocking {
        val numbersFlow = sendNewNumbers()
        println("Flow hasnt't started yet")
        println("Starting flow now")

        withTimeoutOrNull(1000L) {
            numbersFlow.collect { println(it) }
        }

//        numbersFlow.collect {
//            println("${it}")
//        }
    }
}

//fun sendNewNumbers() = listOf(1, 2, 3).asFlow()

fun sendNewNumbers() = flow {
    listOf(1, 2, 3).forEach {
        kotlinx.coroutines.delay(400L)
        emit(it)
    }
}