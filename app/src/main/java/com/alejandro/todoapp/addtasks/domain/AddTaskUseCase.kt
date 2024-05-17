package com.alejandro.todoapp.addtasks.domain

import com.alejandro.todoapp.addtasks.data.TaskRepository
import com.alejandro.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.add(taskModel)
    }
}