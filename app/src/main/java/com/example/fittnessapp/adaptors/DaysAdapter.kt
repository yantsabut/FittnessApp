package com.example.fittnessapp.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fittnessapp.R
import com.example.fittnessapp.databinding.DayListItemBinding

class DaysAdapter: ListAdapter <DayModel,DaysAdapter.DaysHolder> (MyComparator()) {

    class DaysHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = DayListItemBinding.bind(view)

        fun setData(day: DayModel) = with(binding) {
            val name = root.context.getString(R.string.day) + " ${adapterPosition + 1}"
            tvName.text = name
            val exCounter = day.exercises.split(",").size.toString()
            exirciseCounter.text = exCounter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.day_list_item, parent, false)
        return DaysHolder(view)

    }

    override fun onBindViewHolder(holder: DaysHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyComparator : DiffUtil.ItemCallback<DayModel>() {
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem

        }
    }
}





