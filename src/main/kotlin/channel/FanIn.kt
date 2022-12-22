package channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel

fun main() {
    runBlocking {
        val channel = Channel<String>()
        launch { sendString(channel, "message1", 200L) }
        launch { sendString(channel, "message2", 500L) }
        repeat(6) { println(channel.receive()) }
        coroutineContext.cancelChildren()
    }
}

suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while(true) {
        delay(time)
        channel.send(s)
    }
}