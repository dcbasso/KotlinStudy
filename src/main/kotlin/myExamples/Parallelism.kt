package myExamples

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        asyncMethods()
        awaitAllMethods()
        noWaitMethods()
    }
}

suspend fun noWaitMethods() = coroutineScope {
    var one = 0
    var two = 0
    val time = measureTimeMillis {
        one = touchDatabaseOne()
        two = touchDatabaseTwo()
    }
    showResult(time, one+two, "NO WAITING")
}

suspend fun asyncMethods() = coroutineScope {
    var total = 0
    val time = measureTimeMillis {
        val a = async { touchDatabaseOne() }
        val b = async { touchDatabaseTwo() }
        total = a.await() + b.await()
    }
    showResult(time, total, "ASYNC")
}

suspend fun awaitAllMethods() = coroutineScope {
    var one = 0
    var two = 0
    val time = measureTimeMillis {
        awaitAll(
            async {
                one = touchDatabaseOne()
            },
            async {
                two = touchDatabaseTwo()
            }
        )
    }
    showResult(time, one+two, "AWAITALL")
}

fun showResult(time: Long, total: Int, method: String) {
    println("---> Completed two actions, with total value $total, in ${time}ms using: $method")
}

suspend fun touchDatabaseOne() : Int {
    delay(4500L)
    return 1000
}

suspend fun touchDatabaseTwo() : Int {
    delay(3000L)
    return 2000
}