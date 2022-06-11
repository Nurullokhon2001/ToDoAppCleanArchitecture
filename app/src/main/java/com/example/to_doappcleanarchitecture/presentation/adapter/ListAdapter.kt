package com.example.to_doappcleanarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doappcleanarchitecture.R
import com.example.to_doappcleanarchitecture.data.model.ToDoData
import com.example.to_doappcleanarchitecture.presentation.model.Priority
import com.example.to_doappcleanarchitecture.presentation.ui.ListFragmentDirections

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var data = emptyList<ToDoData>()

    fun submitList(toDoData: List<ToDoData>) {
        this.data = toDoData
        notifyDataSetChanged()
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = itemView.findViewById(R.id.title_text)
        val tvDescription: TextView = itemView.findViewById(R.id.description_text)
        val cvPriority: CardView = itemView.findViewById(R.id.priority_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rov_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data[position]
        holder.tvTitle.text = data.title
        holder.tvDescription.text = data.description

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(data)
            holder.itemView.findNavController().navigate(action)
        }

        when (data.priority) {
            Priority.HIGH -> {
                holder.cvPriority.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.red
                    )
                )
            }
            Priority.MEDIUM -> {
                holder.cvPriority.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.yellow
                    )
                )
            }
            Priority.LOW -> {
                holder.cvPriority.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.green
                    )
                )
            }
        }
    }

    override fun getItemCount() = data.size
}