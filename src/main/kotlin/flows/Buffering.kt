package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            generate()
                .buffer()
                .collect{
                    delay(300L)
                    println(it)
                }
        }
        println("Collected in $time ms")
    }
}


fun generate() = flow {
    for (i in 1..3) {
        kotlinx.coroutines.delay(100L)
        emit(i)
    }
}