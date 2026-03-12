package com.example.rastiapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class ApiTodo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

interface ApiTest {
    @GET("todos/1")
    suspend fun getTodo(): ApiTodo
}

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
        private set

    var apiResult by mutableStateOf("Ladataan...")
        private set

    private val api = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiTest::class.java)

    init {
        tasks = listOf(
            Task(1, "Tee rästiapp"),
            Task(2, "Käy koululla"),
            Task(3, "Siivoa kämppä"),
            Task(4, "Lue tenttiin")
        )
        fetchTodo()
    }

    fun toggleDone(id: Int) {
        tasks = tasks.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun addTask(title: String) {
        val nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
        tasks = tasks + Task(nextId, title)
    }

    private fun fetchTodo() {
        viewModelScope.launch {
            try {
                val todo = api.getTodo()
                apiResult = todo.title
            } catch (e: Exception) {
                apiResult = "Virhe: ${e.message}"
            }
        }
    }
}