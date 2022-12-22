package channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

fun main() {
    runBlocking {
        val producer = produceNumbersWithDelay()

        repeat(5) {
            launchProcessor(it, producer)
        }
        delay(2000L)
        producer.cancel()
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel)
        println("Processor #$id received $msg")
}

fun CoroutineScope.produceNumbersWithDelay() = produce<Int> {
    var x = 1
    while(true) {
        send(x++)
        delay(100L)
    }
}

//fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
//    for (x in numbers)
//        send(x*x)
//}