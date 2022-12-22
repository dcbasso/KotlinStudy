package flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        sendNumbers().collect {
            println("Received: $it")
        }
    }
}

fun sendNumbers() =
    flowOf(1, 2, 3, 5, 6)

//    listOf(1, 2, 3).asFlow()

//= flow {
//    for (i in 1..10) {
//        emit(i)
//    }
//}