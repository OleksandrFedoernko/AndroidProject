package com.example.androidprojecttodoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(val listener: TaskClickListener) :
    RecyclerView.Adapter<TasksAdapter.WeblinksViewHolder>() {

    var cachedTasks: List<Task> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class WeblinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textViewDesc: TextView = itemView.findViewById(R.id.textDesc)

        fun bind(task: Task, listener: TaskClickListener) {
            textViewTitle.text = task.title
            textViewDesc.text = task.description


            textViewTitle.setOnLongClickListener {
                listener.onWeblinkLongClick(task)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeblinksViewHolder {

        return LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_layout,
                parent, false
            ).let {
                WeblinksViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: WeblinksViewHolder, position: Int) {
        holder.bind(cachedTasks[position], listener)
    }

    override fun getItemCount() = cachedTasks.size

}