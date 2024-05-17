package com.alejandro.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alejandro.todoapp.addtasks.ui.TasksScreen
import com.alejandro.todoapp.addtasks.ui.TasksViewModel
import com.alejandro.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val taskViewModel:TasksViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize().padding(vertical = 40.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TasksScreen(taskViewModel)

                }
            }
        }
    }
}

