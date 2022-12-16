package com.example.androidprojecttodoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskRVAdapter(
    val context: Context,
    val taskClickInterface: TaskClickInterface,
    val taskClickDeleteInterface: TaskClickDeleteInterface
) : RecyclerView.Adapter<TaskRVAdapter.ViewHolder>() {

    private val allTask = ArrayList<Task>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTV = itemView.findViewById<TextView>(R.id.TaskTitle)
        val timeTV = itemView.findViewById<TextView>(R.id.TimeStamp)
        val deleteTV = itemView.findViewById<ImageView>(R.id.DeleteBTN)
    }

    interface TaskClickDeleteInterface {
        fun onDeleteIconClick(task: Task)
    }

    interface TaskClickInterface {
        fun onTaskClick(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.task_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTV.setText(allTask.get(position).tasksTitle)
        holder.timeTV.setText("Last updated: " + allTask.get(position).timeStamp)
        holder.deleteTV.setOnClickListener {
            taskClickDeleteInterface.onDeleteIconClick(allTask.get(position))
        }
        holder.itemView.setOnClickListener {
            taskClickInterface.onTaskClick(allTask.get(position))

        }
    }

    override fun getItemCount(): Int {
        return allTask.size
    }
    fun updateList(newList: List<Task>){
        allTask.clear()
        allTask.addAll(newList)
        notifyDataSetChanged()
    }
}

