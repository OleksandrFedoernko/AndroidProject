package com.example.androidprojecttodoapp

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeDelete (val adapter: TasksAdapter, val viewModel: TViewModel)
    : ItemTouchHelper.SimpleCallback(0,
    ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val index = viewHolder.adapterPosition
        viewModel.delete(adapter.cachedTasks[index])
    }
}