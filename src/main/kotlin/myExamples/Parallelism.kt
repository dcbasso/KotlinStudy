package myExamples

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        awaitAllMethods()
        noWaitMethods()
        asyncMethods()
    }
}

suspend fun noWaitMethods() = coroutineScope {
    val time = measureTimeMillis {
        touchDatabaseOne()
        touchDatabaseTwo()
    }
    println("---> completed two actions in $time ms not waiting")
}

suspend fun asyncMethods() = coroutineScope {
    val time = measureTimeMillis {
        val a = async { touchDatabaseOne() }
        val b = async { touchDatabaseTwo() }
        val total = a.await() + b.await()
    }
    println("---> completed two actions in $time ms WITH ASYNC")
}

suspend fun awaitAllMethods() = coroutineScope {
    val time = measureTimeMillis {
        awaitAll(
            async {
                touchDatabaseOne()
            },
            async {
                touchDatabaseTwo()
            }
        )
    }
    println("---> completed two actions in $time ms using AWAITALL")
}

suspend fun touchDatabaseOne() : Int {
    delay(4500L)
    println("finish touchDatabaseOne")
    return 1000
}

suspend fun touchDatabaseTwo() : Int {
    delay(3000L)
    println("finish touchDatabaseTwo")
    return 2000
}