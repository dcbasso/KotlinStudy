package sharedstate

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        var counterContext = newSingleThreadContext("CounterContext")
        var counter = 0
        withContext(counterContext) {
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