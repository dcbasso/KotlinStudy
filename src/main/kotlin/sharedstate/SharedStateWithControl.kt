package sharedstate

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        var counter = AtomicInteger()
        withContext(Dispatchers.Default) {
            massiveRun { counter.incrementAndGet() }
        }
        println("Counter = $counter")
    }
}
