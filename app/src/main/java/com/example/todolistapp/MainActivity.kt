package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.todolistapp.ui.theme.TODOLISTAPPTheme
import com.example.todolistapp.viewmodel.TaskViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistapp.model.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOLISTAPPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskScreen(viewModel = hiltViewModel())
                }
            }
        }
    }
}

@Composable
fun TaskScreen(viewModel: TaskViewModel= hiltViewModel()) {
    val tasks by viewModel.allTasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TaskInput { title, description ->
            viewModel.addTask(Task(title = title, description = description))
        }
        Spacer(modifier = Modifier.height(16.dp))
        TaskList(tasks)
    }
}

@Composable
fun TaskInput(onTaskAdded: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Task Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Task Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onTaskAdded(title, description)
                    title = ""
                    description = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Task")
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>) {
    Column {
        for (task in tasks) {
            TaskItem(task)
            Divider()
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Text(text = "${task.title}: ${task.description}")
}
