import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch(CoroutineName("firstCoroutineNamed")) {
            println("This is run from ${coroutineContext.get(CoroutineName.Key)}")
        }
    }
}