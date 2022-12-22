import kotlinx.coroutines.*

fun main() {
    val myHandler = CoroutineExceptionHandler {
        coroutineContext, throwable -> println("Exception: ${throwable.localizedMessage}")
    }
    runBlocking {
        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            throw IndexOutOfBoundsException()
        }
        job.join()

        val deferred = GlobalScope.async {
            println("Throwing from async")
            throw ArithmeticException("exception from async")
        }

        try {
            deferred.await()
        }catch (e: ArithmeticException) {
            println("deferred exception: ${e.localizedMessage}")
        }
    }
}