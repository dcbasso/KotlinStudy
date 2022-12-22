package sharedstate

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val mutex = Mutex()
        var counter = 0


        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock { counter++ }
            }
        }
        println("Counter = $counter")
    }
}
