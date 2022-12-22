package sharedstate

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        var counterContext = newSingleThreadContext("CounterContext")
        var counter = 0
        withContext(Dispatchers.Default) {
            //use only if there is something todo here
            massiveRunThreads {
                withContext(counterContext) {
                    counter++
                }
            }
        }
        println("Counter = $counter")
    }
}

suspend fun massiveRunThreads(action: suspend() -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("completed ${n * k} actions in $time ms")
}