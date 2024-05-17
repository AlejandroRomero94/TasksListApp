package com.alejandro.todoapp.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandro.todoapp.addtasks.domain.AddTaskUseCase
import com.alejandro.todoapp.addtasks.domain.DeleteTaskUseCase
import com.alejandro.todoapp.addtasks.domain.GetTaskUseCase
import com.alejandro.todoapp.addtasks.domain.UpdateUseCase
import com.alejandro.todoapp.addtasks.ui.TasksUiState.Error
import com.alejandro.todoapp.addtasks.ui.TasksUiState.Loading
import com.alejandro.todoapp.addtasks.ui.TasksUiState.Success
import com.alejandro.todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateUseCase: UpdateUseCase,
    private val deleteUseCase: DeleteTaskUseCase,
    getTaskUseCase: GetTaskUseCase
) :
    ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTaskUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)


    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog



    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(task: String) {
        _showDialog.value = false



        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }

    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {

        viewModelScope.launch {
            updateUseCase(taskModel.copy(selected=!taskModel.selected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
viewModelScope.launch{
    deleteUseCase(taskModel)
}
    }
}