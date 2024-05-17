package com.alejandro.todoapp.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class TaskEntity (
    @PrimaryKey
val id:Long=System.currentTimeMillis(),
val task:String,
var selected:Boolean=false


)