package com.example.rastiapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: TaskViewModel = viewModel()) {
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "RastiApp",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // api-tulos
        Text(text = "API-result: ${viewModel.apiResult}")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Uusi tehtävä") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        viewModel.addTask(newTaskTitle)
                        newTaskTitle = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.tasks, key = { it.id }) { task ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )
                    Text(
                        text = task.title,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}